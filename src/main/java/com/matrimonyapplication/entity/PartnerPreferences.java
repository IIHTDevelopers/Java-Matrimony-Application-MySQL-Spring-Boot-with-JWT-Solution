package com.matrimonyapplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "partner_preferences")
public class PartnerPreferences {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "preferred_age_start")
	private Integer preferredAgeStart;

	@Column(name = "preferred_age_end")
	private Integer preferredAgeEnd;

	@Column(name = "preferred_gender")
	private String preferredGender;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	// Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPreferredAgeStart() {
		return preferredAgeStart;
	}

	public void setPreferredAgeStart(Integer preferredAgeStart) {
		this.preferredAgeStart = preferredAgeStart;
	}

	public Integer getPreferredAgeEnd() {
		return preferredAgeEnd;
	}

	public void setPreferredAgeEnd(Integer preferredAgeEnd) {
		this.preferredAgeEnd = preferredAgeEnd;
	}

	public String getPreferredGender() {
		return preferredGender;
	}

	public void setPreferredGender(String preferredGender) {
		this.preferredGender = preferredGender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}