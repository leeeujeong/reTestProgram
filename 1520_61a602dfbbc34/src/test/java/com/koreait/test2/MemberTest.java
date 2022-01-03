package com.koreait.test2;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.test2.dao.MemberDAO;
import com.koreait.test2.dto.Member;

public class MemberTest {

	@Autowired
	private SqlSession sqlSession;

	@Test
	public void idCheckTest() {
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		assertNotNull("아이디 체크 실패", memberDAO.idCheck("admin"));
	}
	@Test
	public void loginTest() {
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		Member member = new Member();
		member.setName("admin");
		member.setPw("1111");
		
		assertNotNull("로그인 테스트 실패", memberDAO.login(member));
		assertNotNull("로그인 테스트 실패", memberDAO.login(member));
		
		
	}
	
	@Test
	public void joinTest() {
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		Member member = new Member();
		member.setNo(member.getNo());
		member.setId("textId");
		member.setPw("testPw");
		member.setName("testName");
		member.setEmail("testId@wdb.com");
		
		assertNotNull("가입 테스트 실패", memberDAO.join(member));
	}
	
	@Test
	public void leaveTest() {
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		Member member = new Member();
		Long no = member.getNo();
		member.setNo(no);
		assertNotNull("탈퇴 테스트 실패", memberDAO.join(member));
	}


}
