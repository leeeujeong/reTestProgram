package com.koreait.test2.dao;

import com.koreait.test2.dto.Member;

public interface MemberDAO {
	
	public Member login(Member member);
	public int join(Member member);
	public int leave(Long no);
	public int idCheck(String id);
	
}
