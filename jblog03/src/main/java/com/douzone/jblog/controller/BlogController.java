package com.douzone.jblog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
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
	
	@Autowired
	FileUploadService fileUploadService;
	
	
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
		List<CategoryVo> categoryList =categoryService.getCategories(id);
		postVo = postService.getPostContents( categoryNo,postNo);
		List<PostVo> postList = postService.getPostList(categoryNo);
			
		model.addAttribute("blogInfo",blogVo);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("postInfo",postVo);
		model.addAttribute("postList",postList);
		
		return "blog/main";
	}
	
	
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, @AuthUser UserVo authUser, BlogVo blogVo, Model model) {
		
		if( authUser == null  || !authUser.getId().equals(id) ) {
			return "redirect:/main";
		}
		
		blogVo=blogService.getBlog(id);
		model.addAttribute("blogInfo",blogVo);
		
		return "blog/admin/basic";
	}
	
	@Auth
	@RequestMapping("/admin/basic/upload")
	public String adminBasic(@PathVariable("id") String id, @RequestParam(value="title", required=true, defaultValue="") String title, @RequestParam("logo-file") MultipartFile multipartFile, Model model, @AuthUser UserVo authUser ) {
		
		if( authUser == null  || !authUser.getId().equals(id) ) {
			return "redirect:/main";
		}
		
		String url =fileUploadService.restoreImage(multipartFile);
		model.addAttribute("url",url);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogId(id);
		blogVo.setLogo(url);
		blogVo.setTitle(title);
		blogService.updateBlogInfo(blogVo);
		return "redirect:/"+id+"/admin/basic";
	}
	
	@Auth
	@RequestMapping("/admin/basic/category")
	public String adminCategory(@PathVariable("id") String id, @AuthUser UserVo authUser, BlogVo blogVo,CategoryVo categoryVo, Model model ) {
		
		if(authUser == null && authUser.getId().equals(id)) {
			return "redirect:/main";
		}
		
		List<CategoryVo> categoryList =categoryService.getCategories(id);
		model.addAttribute("categoryList",categoryList);
		
		
		return "blog/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/basic/category/insert")
	public String adminCategoryInsert(@PathVariable("id") String id, @RequestParam(value = "name",required = true) String categoryName,
			@RequestParam(value = "desc",required = true) String desc,
			@AuthUser UserVo authUser ,CategoryVo categoryVo, Model model ) {
		
		System.out.println("테스트11111111111111111" + id);
		System.out.println("테스트22222222222222222" + categoryName);
		System.out.println("테스트33333333333333333" + desc);
		
		if(authUser == null && authUser.getId().equals(id)) {
			return "redirect:/main";
		}
		
		categoryVo.setBlogId(id);
		categoryVo.setDescription(desc);
		categoryVo.setName(categoryName);
		
		System.out.println("테스트444444444"+categoryVo);
		categoryService.insertCategory(categoryVo);
		
		return "redirect:/"+id+"/admin/basic/category";
	}
	
	
	
}