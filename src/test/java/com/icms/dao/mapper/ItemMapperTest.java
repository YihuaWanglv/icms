package com.icms.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icms.App;
import com.icms.mapper.CategoryMapper;
import com.icms.model.Category;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ItemMapperTest {

	@Autowired CategoryMapper itemMapper;
	
	@Test
	public void test() {
		Category item = itemMapper.selectByPrimaryKey(1);
		Gson g = new GsonBuilder().serializeNulls().create();
		System.out.println("" + g.toJson(item));
	}
}
