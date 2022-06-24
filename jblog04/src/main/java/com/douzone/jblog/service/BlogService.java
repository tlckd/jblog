package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;




@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	public BlogVo getBlog(String id) {
		return blogRepository.findBlogInfo(id); 
	}


	public void updateBlogInfo(BlogVo blogVo) {
		blogRepository.update(blogVo);
		
	}
	

	
	
	
	
}
