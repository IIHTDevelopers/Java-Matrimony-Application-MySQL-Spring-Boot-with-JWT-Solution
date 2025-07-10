package com.matrimonyapplication.dto;

public class PartnerPreferencesDto {

    private Long id;
    private Integer preferredAgeStart;
    private Integer preferredAgeEnd;
    private String preferredGender;
    private Long userId; // To link PartnerPreferencesDto to a UserDto

    // Constructors, getters, and setters

    public PartnerPreferencesDto() {
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}