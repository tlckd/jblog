package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo findByIDAndPassword(UserVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.findByIDAndPassword", vo);
	}

	public void insert(UserVo vo) {
		sqlSession.insert("user.userInsert", vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", vo.getId());
		map.put("blogTitle",vo.getName() + "님의 블로그");
		map.put("blogLogo","/assets/images/default.jpg");
		map.put("categoryName", "기타");
		map.put("categoryDescription","기타 태그입니다.");
		
		sqlSession.insert("user.userInsertBlog", map);
		sqlSession.insert("user.userInsertCategory", map);
			
	}

}
