package com.board.icia;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.Member;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;


//@Slf4j
@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
@Transactional  //테스트 조작시 자동으로 롤백처리해줌
public class MemberDaoTest {
	@Autowired
	private IMemberDao dao;	
	@Test
	public void loginTest() {
		log.info("dao="+dao);
		assertThat(dao,is(notNullValue())); 
		//log.info("dao={}",dao);  //{} 는 C언어의 %d 와 같은 역할
		Member mb=new Member().setM_id("aaa").setM_pwd("1111");
		assertThat(dao.getLoginResult(mb),is(true));
		//assertThat(dao, is(nullValue()));
		mb=dao.getMemberInfo("aaa");
		assertThat(mb.getM_pwd(), is(nullValue()));
		assertThat(mb.getM_name(), is("김지영"));
		
	}
	@Test
	public void loginTest2() {
		log.info("dao="+dao);
		assertThat(dao,is(notNullValue())); 
		//log.info("dao={}",dao);  //{} 는 C언어의 %d 와 같은 역할
		Member mb=new Member().setM_id("aaa").setM_pwd("1111");
		assertThat(dao.getLoginResult(mb),is(true));
		//assertThat(dao, is(nullValue()));
		mb=dao.getMemberInfo("aaa");
		assertThat(mb.getM_pwd(), is(nullValue()));
		assertThat(mb.getM_name(), is("김지영"));
		
	}
	/*
	 * @Test public void xxxTest() { log.info("dao=",dao); Member
	 * mb=dao.getMemberInfo("AAA"); assertThat(dao, is(notNullValue())); }
	 */
}
