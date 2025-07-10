package com.matrimonyapplication.functional;

import static com.matrimonyapplication.utils.TestUtils.businessTestFile;
import static com.matrimonyapplication.utils.TestUtils.currentTest;
import static com.matrimonyapplication.utils.TestUtils.testReport;
import static com.matrimonyapplication.utils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.matrimonyapplication.controller.PartnerPreferencesController;
import com.matrimonyapplication.dto.PartnerPreferencesDto;
import com.matrimonyapplication.entity.PartnerPreferences;
import com.matrimonyapplication.service.PartnerPreferencesService;

@ExtendWith(MockitoExtension.class)
public class PartnerPreferencesControllerTest {

	@Mock
	private PartnerPreferencesService partnerPreferencesService;

	@InjectMocks
	private PartnerPreferencesController partnerPreferencesController;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetPartnerPreferencesByUserId_WhenExpenseExists() throws Exception {
		try {
			Long expenseId = 1L;
			PartnerPreferences expense = new PartnerPreferences();
			PartnerPreferencesDto partnerDto = new PartnerPreferencesDto();
			expense.setId(expenseId);
			ResponseEntity<PartnerPreferencesDto> response = partnerPreferencesController.getPreferences(expenseId);
			if (response == null) {
				yakshaAssert(currentTest(), false, businessTestFile);
			} else {
				yakshaAssert(currentTest(),
						response.getStatusCode() == HttpStatus.OK && response.getStatusCode() == HttpStatus.OK,
						businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testCreatePreferences() throws Exception {
		try {
			Long userId = 1L;
			PartnerPreferencesDto partnerPreferencesDto = new PartnerPreferencesDto();
			ResponseEntity<PartnerPreferencesDto> response = partnerPreferencesController
					.createPreferences(partnerPreferencesDto, userId);
			if (response == null) {
				yakshaAssert(currentTest(), false, businessTestFile);
			} else {
				yakshaAssert(currentTest(), response.getStatusCode() == HttpStatus.OK, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testUpdatePreferences() throws Exception {
		try {
			Long userId = 1L;
			PartnerPreferencesDto partnerPreferencesDto = new PartnerPreferencesDto();
			ResponseEntity<PartnerPreferencesDto> response = partnerPreferencesController.updatePreferences(userId,
					partnerPreferencesDto);
			if (response == null) {
				yakshaAssert(currentTest(), false, businessTestFile);
			} else {
				yakshaAssert(currentTest(), response.getStatusCode() == HttpStatus.OK, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDeletePreferences() throws Exception {
		try {
			Long userId = 1L;
			ResponseEntity<?> response = partnerPreferencesController.deletePreferences(userId);
			if (response == null) {
				yakshaAssert(currentTest(), false, businessTestFile);
			} else {
				yakshaAssert(currentTest(), response.getStatusCode() == HttpStatus.OK, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}
}