package com.icms.web.controller.admin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.icms.model.Category;
import com.icms.service.remote.CategoryRemote;
import com.icms.service.remote.PostRemote;
import com.icms.web.dto.CategoryView;



@RestController
@RequestMapping("/admin")
public class AdminCategoryController {
	
	@Autowired PostRemote postService;
	@Autowired CategoryRemote categoryService;
	
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryView> listCategory() {
//		if (pid == null) {
//			pid = 0;
//		}
		List<CategoryView> ivs = new ArrayList<CategoryView>();
		List<Category> items = categoryService.getListByParentId(0);
		for (Category item : items) {
			CategoryView iv = new CategoryView();
			BeanUtils.copyProperties(item, iv);
			ivs.add(iv);
		}
		return ivs;
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseBody
	public Category addCategory(@RequestBody Category category) {
		category.setPid(0);
		category.setDeleted(0);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		category.setStatus(0);
		category.setType(0);
		category.setDescr("");
		categoryService.save(category, null);
		System.err.println(new Gson().toJson(category));
		return category;
	}
	
	@RequestMapping(value = "/category/{cid}", method = RequestMethod.PUT)
	@ResponseBody
	public CategoryView updateCategory(@RequestBody CategoryView category, @PathVariable Integer cid) {
		category.setCid(cid);
		categoryService.save(category, false);
		return category;
	}
	
	@RequestMapping(value = "/category/{cid}", method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable Integer cid) {
		categoryService.delete(cid);
	}
	
}
