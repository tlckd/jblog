package com.douzone.jblog.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;


@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	public UserVo getUser(String id, String password) {
		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setPassword(password);
		
		return getUser(vo);
	}
	
	public UserVo getUser(UserVo vo) {
		return userRepository.findByIDAndPassword(vo);
	}

	public void join(UserVo userVo) {
		userRepository.insert(userVo);
	}
	
	
}
