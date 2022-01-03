package com.koreait.test2.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface MemberService {
	public void execute(SqlSession sqlSession, Model model);
}
