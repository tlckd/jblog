package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"}) //들어오는게 여러가지 형식이기 때문에 { } 로 멀티매핑 
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) { // 값이 있는지 없는지 체크 
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		//categoryService.getCategories(id, categoryNo);
		return "main/index";
	}
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		
		return id;

	}
}