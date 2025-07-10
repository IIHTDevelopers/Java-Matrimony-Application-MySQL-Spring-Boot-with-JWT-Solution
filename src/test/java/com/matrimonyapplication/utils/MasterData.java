package com.matrimonyapplication.utils;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.matrimonyapplication.dto.PartnerPreferencesDto;
import com.matrimonyapplication.dto.UserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class MasterData {

	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	private static Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public static String getToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public static PartnerPreferencesDto getPartnerPreferencesDto() {
		PartnerPreferencesDto dto = new PartnerPreferencesDto();
		dto.setId(1L);
		dto.setPreferredAgeStart(25);
		dto.setPreferredAgeEnd(30);
		dto.setPreferredGender("Female");
		dto.setUserId(1L);
		return dto;
	}

	public static UserDto getUserDTO() {
		UserDto dto = new UserDto();
		dto.setId(1L);
		dto.setEmail("john.doe@example.com");
		dto.setName("John Doe");
		dto.setPassword("password");
		dto.setDob(new Date());
		dto.setGender("Male");
		return dto;
	}

	public static List<UserDto> getUserDtoList() {
		List<UserDto> userDtoList = new ArrayList<>();
		UserDto dto = new UserDto();
		dto.setId(1L);
		dto.setEmail("john.doe@example.com");
		dto.setName("John Doe");
		dto.setPassword("password");
		dto.setDob(new Date());
		dto.setGender("Male");
		userDtoList.add(dto);
		return userDtoList;
	}

	public static List<PartnerPreferencesDto> getPartnerPreferencesDtoList() {
		List<PartnerPreferencesDto> partnerPreferencesDTOList = new ArrayList<>();
		PartnerPreferencesDto dto = new PartnerPreferencesDto();
		dto.setId(1L);
		dto.setPreferredAgeStart(25);
		dto.setPreferredAgeEnd(30);
		dto.setPreferredGender("Female");
		dto.setUserId(1L);
		partnerPreferencesDTOList.add(dto);
		return partnerPreferencesDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		String s = "";
		for (int i = 0; i < size; i++) {
			s.concat("A");
		}
		return s;
	}
}