package com.matrimonyapplication.boundary;

import static com.matrimonyapplication.utils.TestUtils.boundaryTestFile;
import static com.matrimonyapplication.utils.TestUtils.currentTest;
import static com.matrimonyapplication.utils.TestUtils.testReport;
import static com.matrimonyapplication.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.matrimonyapplication.dto.UserDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UserBoundaryTest {

	private static Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testNameNotBlank() throws IOException {
		UserDto userDto = new UserDto();
		userDto.setName("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testEmailNotBlankAndValid() throws IOException {
		UserDto userDto = new UserDto();
		userDto.setEmail(""); // Testing NotBlank
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		boolean notBlankViolated = !violations.isEmpty();

		userDto.setEmail("invalidemail"); // Testing Email format
		violations = validator.validate(userDto);
		boolean formatViolated = !violations.isEmpty();

		try {
			yakshaAssert(currentTest(), notBlankViolated && formatViolated, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPasswordNotBlank() throws IOException {
		UserDto userDto = new UserDto();
		userDto.setPassword("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testGenderNotBlank() throws IOException {
		UserDto userDto = new UserDto();
		userDto.setGender(""); // Testing NotBlank
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}