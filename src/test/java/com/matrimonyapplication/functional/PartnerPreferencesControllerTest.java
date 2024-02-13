package com.matrimonyapplication.functional;

import static com.matrimonyapplication.utils.TestUtils.businessTestFile;
import static com.matrimonyapplication.utils.TestUtils.currentTest;
import static com.matrimonyapplication.utils.TestUtils.testReport;
import static com.matrimonyapplication.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

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
			when(partnerPreferencesService.getPartnerPreferences(expenseId)).thenReturn(partnerDto);
			ResponseEntity<PartnerPreferencesDto> response = partnerPreferencesController.getPreferences(expenseId);
			yakshaAssert(currentTest(),
					response.getStatusCode() == HttpStatus.OK && response.getStatusCode() == HttpStatus.OK,
					businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testCreatePreferences() throws Exception {
		Long userId = 1L;
		PartnerPreferencesDto partnerPreferencesDto = new PartnerPreferencesDto();
		when(partnerPreferencesService.createPartnerPreferences(userId, partnerPreferencesDto))
				.thenReturn(partnerPreferencesDto);
		ResponseEntity<PartnerPreferencesDto> response = partnerPreferencesController
				.createPreferences(partnerPreferencesDto, userId);
		yakshaAssert(currentTest(),
				response.getStatusCode() == HttpStatus.OK && response.getBody().equals(partnerPreferencesDto),
				businessTestFile);
	}

	@Test
	public void testUpdatePreferences() throws Exception {
		Long userId = 1L;
		PartnerPreferencesDto partnerPreferencesDto = new PartnerPreferencesDto();
		when(partnerPreferencesService.updatePartnerPreferences(userId, partnerPreferencesDto))
				.thenReturn(partnerPreferencesDto);
		ResponseEntity<PartnerPreferencesDto> response = partnerPreferencesController.updatePreferences(userId,
				partnerPreferencesDto);
		yakshaAssert(currentTest(),
				response.getStatusCode() == HttpStatus.OK && response.getBody().equals(partnerPreferencesDto),
				businessTestFile);
	}

	@Test
	public void testDeletePreferences() throws Exception {
		Long userId = 1L;
		ResponseEntity<?> response = partnerPreferencesController.deletePreferences(userId);
		yakshaAssert(currentTest(), response.getStatusCode() == HttpStatus.OK, businessTestFile);
	}
}