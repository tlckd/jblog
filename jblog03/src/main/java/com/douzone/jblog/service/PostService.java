package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;


@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public PostVo getPostContents(Long categoryNo, Long postNo) {
		return postRepository.findPostContents(categoryNo,postNo);
	}
	
	public List<PostVo> getPostList(Long categoryNo){
		return postRepository.findPostList(categoryNo);
	}
}
