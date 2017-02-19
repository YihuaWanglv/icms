package com.icms.service.remote;

import com.icms.model.Config;

public interface ConfigRemote {

	Config getById(Integer id);
	
	Config getByName(String name);
	
	Config getConfigOfHome();
	
	void save(Config point, Boolean isAllField);
	
}
