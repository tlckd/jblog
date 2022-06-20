package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
		
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> findCategoryInfo(String id, Long categoryNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		
		return sqlSession.selectList("category.findCategoryInfo",map);
		
	}

}
