//package com.icms.dao.repo;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.icms.model.Config;
//import com.icms.model.TemplateHome;
//import com.icms.model.TemplateItem;
//import com.icms.repository.ConfigRepository;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class ConfigRepoTest {
//
//	@Autowired ConfigRepository configRepository;
//	
////	@Test
//	public void save() {
//		
//		Gson g = new GsonBuilder().serializeNulls().create();
//		
//		TemplateHome th = new TemplateHome();
//		th.setIsOnWebSiteHeader(1);
//		th.setWebSiteName("web site name");
//		th.setWebSiteDetail("web site detail");
//		th.setIsOnHeadline(1);
//		th.setHeadlineCategoryId(1);
//		th.setHeadlinePostId(1);
//		
//		List<TemplateItem> tis = new ArrayList<TemplateItem>();
//		List<Integer> staticPosts = new ArrayList<Integer>();
//		staticPosts.add(1);
//		TemplateItem ti = new TemplateItem();
//		ti.setCategoryId(2);
//		ti.setName("itemname");
//		ti.setTemplateId(1);
//		ti.setTiid(1);
//		ti.setIndexs(1);
//		ti.setStaticPosts(staticPosts);
//		tis.add(ti);
//		th.setTemplateItems(tis);
//		
//		Config config = new Config();
//		config.setCname("home2");
//		config.setContent(g.toJson(th));
//		config.setCreated(new Date());
//		config.setUpdated(new Date());
//		config.setEditor(1);
//		config.setStatus(0);
//		configRepository.save(config);
//		
//		System.out.println("------------------------ ok -------------------------");
//	}
//	
//	@Test
//	public void findHome() {
//		Gson g = new GsonBuilder().serializeNulls().create();
//		Config config = configRepository.findByCname("home2");
//		
//		String content = config.getContent();
//		TemplateHome th = g.fromJson(content, TemplateHome.class);
//		
//		System.out.println(g.toJson(config));
//		System.err.println(g.toJson(th));
//	}
//	
//	
//}
