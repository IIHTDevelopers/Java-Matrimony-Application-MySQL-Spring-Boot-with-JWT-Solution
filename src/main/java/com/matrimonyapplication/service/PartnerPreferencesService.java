package com.matrimonyapplication.service;

import com.matrimonyapplication.dto.PartnerPreferencesDto;

public interface PartnerPreferencesService {
	PartnerPreferencesDto createPartnerPreferences(Long userId, PartnerPreferencesDto partnerPreferencesDto);

	PartnerPreferencesDto getPartnerPreferences(Long userId);

	PartnerPreferencesDto updatePartnerPreferences(Long userId, PartnerPreferencesDto partnerPreferencesDto);

	void deletePartnerPreferences(Long userId);
}