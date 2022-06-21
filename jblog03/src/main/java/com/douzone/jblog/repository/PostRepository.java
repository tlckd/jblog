package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public PostVo findPostContents(Long categoryNo, Long postNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		
		
		return sqlSession.selectOne("post.findPostContents",map);
		
	}
	
	public List<PostVo> findPostList(Long categoryNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryNo", categoryNo);
		
		return sqlSession.selectList("post.findPostList",map);
		
	}

	
	
}
