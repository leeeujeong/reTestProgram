package com.koreait.test2.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.koreait.test2.service.IdCheckService;
import com.koreait.test2.service.MemberJoinService;
import com.koreait.test2.service.MemberLeaveService;
import com.koreait.test2.service.MemberLoginService;
import com.koreait.test2.service.MemberLogoutService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class BeanConfig {
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setUsername("SCOTT");
		hikariConfig.setPassword("1111");
		return hikariConfig;
	}
	@Bean(destroyMethod="close")
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(hikariDataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	@Bean
	public IdCheckService idCheckService() {
		return new IdCheckService();
	}
	@Bean
	public MemberJoinService joinService() {
		return new MemberJoinService();
	}
	@Bean
	public MemberLoginService loginService() {
		return new MemberLoginService();
	}
	@Bean
	public MemberLogoutService logoutService() {
		return new MemberLogoutService();
	}
	@Bean
	public MemberLeaveService leaveService() {
		return new MemberLeaveService();
	}
}
