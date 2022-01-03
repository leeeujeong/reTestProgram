package com.koreait.test2.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.test2.dao.MemberDAO;
import com.koreait.test2.dto.Member;

public class MemberJoinService implements MemberService {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
	
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setEmail(email);
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int result = memberDAO.join(member);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<script>");
				out.println("alert('가입 성공')");
				out.println("location.href='index.do'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('가입 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
