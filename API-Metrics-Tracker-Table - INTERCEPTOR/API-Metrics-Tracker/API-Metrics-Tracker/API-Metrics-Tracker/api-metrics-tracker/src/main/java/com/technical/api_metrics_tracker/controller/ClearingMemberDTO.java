package com.technical.api_metrics_tracker.controller;

import java.time.LocalDateTime;

public class ClearingMemberDTO {

    private Long clearingMemberId;  // Optional: If you need to transfer the member's ID
    private String memberName;
    private String memberType;
    private LocalDateTime registrationDate;  // Store registration date as a LocalDateTime object

    private long responseTime;  // Response time in milliseconds
    private String responseStatus;  // Status of the request

    // Getters and Setters

    public Long getClearingMemberId() {
        return clearingMemberId;
    }

    public void setClearingMemberId(Long clearingMemberId) {
        this.clearingMemberId = clearingMemberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}

