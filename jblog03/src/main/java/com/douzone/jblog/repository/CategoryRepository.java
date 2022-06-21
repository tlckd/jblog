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
	
	public List<CategoryVo> findCategoryInfo(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		
		return sqlSession.selectList("category.findCategoryInfo",map);
		
	}

	public void insertCategory(CategoryVo categoryVo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId",categoryVo.getBlogId());
		map.put("description",categoryVo.getDescription());
		map.put("name",categoryVo.getName());
		
		sqlSession.insert("category.insertCategory",map);
		
	}
}
