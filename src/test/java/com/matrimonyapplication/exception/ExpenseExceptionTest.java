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

import com.matrimonyapplication.dto.PartnerPreferencesDto;
import com.matrimonyapplication.repository.PartnerPreferencesRepository;
import com.matrimonyapplication.repository.UserRepository;
import com.matrimonyapplication.service.PartnerPreferencesService;

@SpringBootTest
public class ExpenseExceptionTest {

	@Autowired
	private PartnerPreferencesService partnerPreferencesService;

	@MockBean
	private PartnerPreferencesRepository partnerPreferencesRepository;

	@MockBean
	private UserRepository userRepository;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreatePartnerPreferencesUserNotFound() throws Exception {
		Long userId = 1101L;
		PartnerPreferencesDto partnerPreferencesDto = new PartnerPreferencesDto();
		when(userRepository.findById(userId)).thenReturn(Optional.empty());
		try {
			partnerPreferencesService.createPartnerPreferences(userId, partnerPreferencesDto);
			yakshaAssert(currentTest(), false, businessTestFile);
		} catch (RuntimeException e) {
			yakshaAssert(currentTest(), true, businessTestFile);
		}
	}

	@Test
	public void testGetPartnerPreferencesNotFound() throws Exception {
		Long userId = 1101L;
		when(partnerPreferencesRepository.findByUserId(userId)).thenReturn(null);
		try {
			partnerPreferencesService.getPartnerPreferences(userId);
			yakshaAssert(currentTest(), false, businessTestFile);
		} catch (RuntimeException e) {
			yakshaAssert(currentTest(), true, businessTestFile);
		}
	}

	@Test
	public void testUpdatePartnerPreferencesNotFound() throws Exception {
		Long userId = 1101L;
		PartnerPreferencesDto partnerPreferencesDto = new PartnerPreferencesDto();
		when(partnerPreferencesRepository.findByUserId(userId)).thenReturn(null);
		try {
			partnerPreferencesService.updatePartnerPreferences(userId, partnerPreferencesDto);
			yakshaAssert(currentTest(), false, businessTestFile);
		} catch (RuntimeException e) {
			yakshaAssert(currentTest(), true, businessTestFile);
		}
	}

	@Test
	public void testDeletePartnerPreferencesNotFound() throws Exception {
		Long userId = 1101L;
		when(partnerPreferencesRepository.findByUserId(userId)).thenReturn(null);
		try {
			partnerPreferencesService.deletePartnerPreferences(userId);
			yakshaAssert(currentTest(), false, businessTestFile);
		} catch (RuntimeException e) {
			yakshaAssert(currentTest(), true, businessTestFile);
		}
	}
}