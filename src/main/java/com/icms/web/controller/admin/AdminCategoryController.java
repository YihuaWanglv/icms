package com.icms.web.controller.admin;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.icms.model.Category;
import com.icms.service.remote.CategoryRemote;
import com.icms.service.remote.PostRemote;
import com.icms.web.dto.CategoryView;



@RestController
@RequestMapping("/admin")
public class AdminCategoryController {
	
	@Autowired PostRemote postService;
	@Autowired CategoryRemote itemService;
	
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryView> listCategory(Integer piid) {
		if (piid == null) {
			piid = 0;
		}
		List<CategoryView> ivs = new ArrayList<CategoryView>();
		List<Category> items = itemService.getListByParentId(piid);
		for (Category item : items) {
			CategoryView iv = new CategoryView();
			BeanUtils.copyProperties(item, iv);
			ivs.add(iv);
		}
		return ivs;
	}
	
	@RequestMapping(value = "/category/save", method = RequestMethod.POST)
	@ResponseBody
	public Category addCategory(Category item) {
		
		itemService.save(item, null);
		return item;
	}
	
	@RequestMapping(value = "/category/{iid}", method = RequestMethod.PUT)
	@ResponseBody
	public CategoryView updateCategory(@RequestBody CategoryView item, @PathVariable Integer cid) {
		item.setCid(cid);
		itemService.save(item, false);
		return item;
	}
	
}
