package com.matrimonyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matrimonyapplication.dto.PartnerPreferencesDto;
import com.matrimonyapplication.service.PartnerPreferencesService;

@RestController
@RequestMapping("/api/preferences")
public class PartnerPreferencesController {

	@Autowired
	private PartnerPreferencesService partnerPreferencesService;

	@PostMapping
	public ResponseEntity<PartnerPreferencesDto> createPreferences(
			@RequestBody PartnerPreferencesDto partnerPreferencesDto, @RequestParam Long userId) {
		PartnerPreferencesDto createdPreferences = partnerPreferencesService.createPartnerPreferences(userId,
				partnerPreferencesDto);
		return ResponseEntity.ok(createdPreferences);
	}

	@GetMapping
	public ResponseEntity<PartnerPreferencesDto> getPreferences(@RequestParam Long userId) {
		PartnerPreferencesDto preferencesDto = partnerPreferencesService.getPartnerPreferences(userId);
		return ResponseEntity.ok(preferencesDto);
	}

	@PutMapping
	public ResponseEntity<PartnerPreferencesDto> updatePreferences(@RequestParam Long userId,
			@RequestBody PartnerPreferencesDto partnerPreferencesDto) {
		PartnerPreferencesDto updatedPreferences = partnerPreferencesService.updatePartnerPreferences(userId,
				partnerPreferencesDto);
		return ResponseEntity.ok(updatedPreferences);
	}

	@DeleteMapping
	public ResponseEntity<?> deletePreferences(@RequestParam Long userId) {
		partnerPreferencesService.deletePartnerPreferences(userId);
		return ResponseEntity.ok().build();
	}
}