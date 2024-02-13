package com.matrimonyapplication.exception;

import static com.matrimonyapplication.utils.TestUtils.businessTestFile;
import static com.matrimonyapplication.utils.TestUtils.currentTest;
import static com.matrimonyapplication.utils.TestUtils.testReport;
import static com.matrimonyapplication.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.matrimonyapplication.repository.UserRepository;
import com.matrimonyapplication.service.impl.UserServiceImpl;

@SpringBootTest
public class UserExceptionTest {

	@Autowired
	private UserServiceImpl userInfoService;

	@MockBean
	private UserRepository repository;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testLoadUserByUsernameUserNotFound() throws Exception {
		try {
			String username = "abc@abc.abc";
			when(repository.findByName(username)).thenReturn(Optional.empty());
			try {
				userInfoService.loadUserByUsername(username);
				yakshaAssert(currentTest(), true, businessTestFile);
			} catch (UsernameNotFoundException e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}
}
