package com.matrimonyapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimonyapplication.entity.PartnerPreferences;

@Repository
public interface PartnerPreferencesRepository extends JpaRepository<PartnerPreferences, Long> {
	PartnerPreferences findByUserId(Long userId);
}