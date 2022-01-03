package com.koreait.test2.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.test2.dao.MemberDAO;
import com.koreait.test2.dto.Member;

public class MemberLeaveService implements MemberService {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		Long no = ((Member)session.getAttribute("loginUser")).getNo();
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int result = memberDAO.leave(no);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				session.invalidate();
				out.println("<script>");
				out.println("alert('탈퇴 성공')");
				out.println("location.href='index.do'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('탈퇴 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
