package com.koreait.test2.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public class MemberLogoutService implements MemberService {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		
		if (session.getAttribute("loginUser") != null) {
			session.invalidate();
		}

	}

}
