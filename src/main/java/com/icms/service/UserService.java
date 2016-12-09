package com.icms.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.icms.mapper.UserMapper;
import com.icms.model.User;
import com.icms.repository.UserRepository;
import com.icms.service.remote.UserRemote;
import com.icms.util.PasswordSecureHash;




@Service
public class UserService implements UserRemote {

	@Autowired private UserRepository userRepository;
	@Autowired private UserMapper userMapper;
	
	@Override
	public User findUserById(Long id) {
		Assert.notNull(id, "userId can not be null!");
		return userRepository.findOne(id);
	}

	@Override
	public User createUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Assert.notNull(user, "User can not be null!");
		String salt = PasswordSecureHash.createRandom();
		user.setPassword(PasswordSecureHash.hashEncrypt(user.getPassword(), salt));
		user.setSalt(salt);
		user.setCode(DigestUtils.sha1DigestAsHex(user.getCode()+salt));
		user = userRepository.save(user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	public User findUserByName(String username) {
		User result = null;
		User user = userRepository.findByName(username);
		if (user != null) {
			result = new User();
			BeanUtils.copyProperties(user, result);
		}
		return result;
	}


	@Override
	public User updateUser(User user, Boolean isUpdateSelected) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Assert.notNull(user, "User can not be null!");
		if (isUpdateSelected != null && isUpdateSelected) {
			int r = userMapper.updateUserSelective(user);
			if (r <= 0) {
				return null;
			}
		} else {
			user = userRepository.save(user);
		}
		return user;
	}


	

}
