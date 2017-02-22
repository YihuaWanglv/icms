package com.icms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icms.model.Config;
import com.icms.repository.ConfigRepository;
import com.icms.service.remote.ConfigRemote;

@Service
public class ConfigService implements ConfigRemote {

	@Autowired private ConfigRepository configRepository;
	
	@Override
	public Config getById(Integer id) {
		return configRepository.findByCid(id);
	}

	@Override
	public Config getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Config getConfigOfHome() {
		return configRepository.findByCname("home");
	}

	@Override
	public void save(Config config) {
		
		configRepository.save(config);
	}

	
}
