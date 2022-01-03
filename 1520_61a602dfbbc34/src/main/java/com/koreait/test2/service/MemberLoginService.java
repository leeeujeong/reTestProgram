package com.koreait.test2.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.test2.dao.MemberDAO;
import com.koreait.test2.dto.Member;

public class MemberLoginService implements MemberService {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member member = new Member();
		member.setId( id);
		member.setPw(pw);
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		Member loginUser = memberDAO.login(member);
		
		if (loginUser != null) {
			request.getSession().setAttribute("loginUser", loginUser);
		}
		
	}

}
