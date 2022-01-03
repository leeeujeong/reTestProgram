package com.koreait.test2.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.test2.service.IdCheckService;
import com.koreait.test2.service.MemberJoinService;
import com.koreait.test2.service.MemberLeaveService;
import com.koreait.test2.service.MemberLoginService;
import com.koreait.test2.service.MemberLogoutService;
@Controller
public class MemberController {

	private SqlSession sqlSession;
	private IdCheckService idCheckService;
	private MemberJoinService joinService;
	private MemberLoginService loginService;
	private MemberLogoutService logoutService;
	private MemberLeaveService leaveService;

	public MemberController(SqlSession sqlSession,
			IdCheckService idCheckService, 
			MemberJoinService joinService,
			MemberLoginService loginService,
			MemberLogoutService logoutService,
			MemberLeaveService leaveService) {
		super();
		this.sqlSession = sqlSession;
		this.idCheckService = idCheckService;
		this.joinService = joinService;
		this.loginService = loginService;
		this.logoutService = logoutService;
		this.leaveService = leaveService;
	}

	@GetMapping(value = { "/", "index.do" })
	public String index() {
		return "index";
	}
	
	@PostMapping(value = "login.do")
	public String login(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		loginService.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@GetMapping(value = "logout.do")
	public String logout(HttpSession session, Model model) {
		model.addAttribute("session", session);
		logoutService.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@GetMapping(value = "joinPage.do")
	public String joinPage() {
		return "member/join";
	}
	
	@GetMapping(value = "idCheck.do", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Integer> idCheck(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return idCheckService.execute(sqlSession, model);
	}
	
	@PostMapping(value = "join.do")
	public void join(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		joinService.execute(sqlSession, model);
	}
	
	@GetMapping(value = "leave.do")
	public void leave(HttpSession session, HttpServletResponse response, Model model) {
		model.addAttribute("session", session);
		model.addAttribute("response", response);
		leaveService.execute(sqlSession, model);
	}

}
