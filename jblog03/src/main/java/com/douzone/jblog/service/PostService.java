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
	
	public PostVo getPostContents(String id, Long categoryNo, Long postNo) {
		return postRepository.findPostContents(id,categoryNo,postNo);
	}
	
	public List<PostVo> getPostList(String id, Long categoryNo){
		return postRepository.findPostList(id,categoryNo);
	}

	public void writePost(String id, int categoryNo, String postTitle, String postContent) {
		postRepository.insertPost(id,categoryNo,postTitle,postContent);
	}
}
