package com.icms.dao.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icms.App;
import com.icms.model.Category;
import com.icms.repository.CategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ItemRepoTest {

	@Autowired CategoryRepository itemRepository;
	
	@Test
	public void test() {
		Category item = itemRepository.findOne(1);
		
		Gson g = new GsonBuilder().serializeNulls().create();
		System.out.println("" + g.toJson(item));
	}
	
	
}
