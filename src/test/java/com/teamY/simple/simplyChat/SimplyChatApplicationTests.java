package com.teamY.simple.simplyChat;

import com.teamY.simple.simplyChat.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SimplyChatApplicationTests {


	@Autowired
	MemberMapper mapper;

	@Test
	void contextLoads() {
		//log.info(mapper.selectMemberByNickname().toString());
		log.info(mapper.selectMemberByNickname("asdf").toString());
	}

}
