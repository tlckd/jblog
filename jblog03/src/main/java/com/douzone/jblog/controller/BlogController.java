package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}") // 정규표현식 asset으로 시작안하는 문자열이 들어오는거 매핑 -> 리소스 매핑해주기
public class BlogController {

	@Autowired
	BlogService blogService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PostService postService;
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"}) //들어오는게 여러가지 형식이기 때문에 { } 로 멀티매핑 
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2, Model model, BlogVo blogVo, CategoryVo categoryVo, PostVo postVo) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) { // 값이 있는지 없는지 체크 
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		blogVo=blogService.getBlog(id);
		List<CategoryVo> categoryList =categoryService.getCategories(id, categoryNo);
		postVo = postService.getPostContents( categoryNo,postNo);
		
		model.addAttribute("blogInfo",blogVo);
		model.addAttribute("categoryList",categoryList);
		
		return "blog/main";
	}
	
	
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, @AuthUser UserVo authUser, BlogVo blogVo, Model model) {
		
		blogVo=blogService.getBlog(id);
		model.addAttribute("blogInfo",blogVo);
		
		if(!authUser.getId().equals(id)) {
			return "redirect:/";
		}
		
		return "blog/admin/basic";
	}
	
	
	
}