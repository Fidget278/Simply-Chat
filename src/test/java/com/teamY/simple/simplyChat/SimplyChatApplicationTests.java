package com.teamY.simple.simplyChat;

import com.teamY.simple.simplyChat.mapper.MemberMapper;
import com.teamY.simple.simplyChat.utill.S3Uploader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SimplyChatApplicationTests {


	@Autowired
	MemberMapper mapper;

	@Autowired
	S3Uploader s3Uploader;
	@Test
	void contextLoads() {
		//log.info(mapper.selectMemberByNickname().toString());
		//log.info(mapper.selectMemberByNickname("asdf").toString());

		s3Uploader.deleteFile("static/b862240f-2172-49ef-86be-c23487f05d87f2c0bd02-d835-48b3-85e1-4c28ae94cd4fbasicProfile.jpg");
	}

}
