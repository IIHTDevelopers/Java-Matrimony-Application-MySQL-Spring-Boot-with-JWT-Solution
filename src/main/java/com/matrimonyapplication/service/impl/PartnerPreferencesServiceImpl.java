package com.matrimonyapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimonyapplication.dto.PartnerPreferencesDto;
import com.matrimonyapplication.entity.PartnerPreferences;
import com.matrimonyapplication.entity.User;
import com.matrimonyapplication.repository.PartnerPreferencesRepository;
import com.matrimonyapplication.repository.UserRepository;
import com.matrimonyapplication.service.PartnerPreferencesService;

@Service
public class PartnerPreferencesServiceImpl implements PartnerPreferencesService {

	@Autowired
	private PartnerPreferencesRepository partnerPreferencesRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public PartnerPreferencesDto createPartnerPreferences(Long userId, PartnerPreferencesDto partnerPreferencesDto) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
		PartnerPreferences preferences = new PartnerPreferences();
		preferences.setUser(user);
		preferences.setPreferredAgeStart(partnerPreferencesDto.getPreferredAgeStart());
		preferences.setPreferredAgeEnd(partnerPreferencesDto.getPreferredAgeEnd());
		preferences.setPreferredGender(partnerPreferencesDto.getPreferredGender());
		PartnerPreferences savedPreferences = partnerPreferencesRepository.save(preferences);
		partnerPreferencesDto.setId(savedPreferences.getId());
		return partnerPreferencesDto;
	}

	@Override
	public PartnerPreferencesDto getPartnerPreferences(Long userId) {
		PartnerPreferences preferences = partnerPreferencesRepository.findByUserId(userId);
		if (preferences == null) {
			throw new RuntimeException("Partner Preferences not found.");
		}
		return convertToDto(preferences);
	}

	@Override
	public PartnerPreferencesDto updatePartnerPreferences(Long userId, PartnerPreferencesDto partnerPreferencesDto) {
		PartnerPreferences preferences = partnerPreferencesRepository.findByUserId(userId);
		if (preferences == null) {
			throw new RuntimeException("Partner Preferences not found.");
		}
		preferences.setPreferredAgeStart(partnerPreferencesDto.getPreferredAgeStart());
		preferences.setPreferredAgeEnd(partnerPreferencesDto.getPreferredAgeEnd());
		preferences.setPreferredGender(partnerPreferencesDto.getPreferredGender());
		PartnerPreferences updatedPreferences = partnerPreferencesRepository.save(preferences);
		return convertToDto(updatedPreferences);
	}

	@Override
	public void deletePartnerPreferences(Long userId) {
		PartnerPreferences preferences = partnerPreferencesRepository.findByUserId(userId);
		if (preferences != null) {
			partnerPreferencesRepository.delete(preferences);
		}
	}

	private PartnerPreferencesDto convertToDto(PartnerPreferences preferences) {
		PartnerPreferencesDto dto = new PartnerPreferencesDto();
		dto.setId(preferences.getId());
		dto.setPreferredAgeStart(preferences.getPreferredAgeStart());
		dto.setPreferredAgeEnd(preferences.getPreferredAgeEnd());
		dto.setPreferredGender(preferences.getPreferredGender());
		dto.setUserId((long) preferences.getUser().getId());
		return dto;
	}
}