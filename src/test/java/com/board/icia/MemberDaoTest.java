package com.board.icia;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/root-context.xml")//Dao에 관련된 xml 경로	(**: 있을 수도 있고 없을 수도 있다.)
public class MemberDaoTest {
	@Autowired
	private IMemberDao dao;
	
	@Test
	public void loginTest() {
		log.info("dao:{}",dao);
		log.info("단위테스트 Junit");
		assertThat(dao,is(notNullValue()));
		Member mb=new Member();
		mb.setM_id("aaa");
		mb.setM_pwd("1111");
		assertThat(dao.getLoginResult(mb), is(true));
		
	}
	
	
}
