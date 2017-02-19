
package com.icms.repository;


import org.springframework.data.repository.CrudRepository;

import com.icms.model.Config;


public interface ConfigRepository extends CrudRepository<Config, Integer> {

	Config findByCname(String cname);
	
	Config findByCid(Integer cid);
}
