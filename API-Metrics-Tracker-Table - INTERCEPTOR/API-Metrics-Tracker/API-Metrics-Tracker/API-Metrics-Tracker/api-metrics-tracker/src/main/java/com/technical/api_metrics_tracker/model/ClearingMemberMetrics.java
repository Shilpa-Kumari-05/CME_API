package com.technical.api_metrics_tracker.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "clearing_member_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClearingMemberMetrics {
    @Id
    private Long memberId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "member_id", nullable = false)
    private ClearingMember clearingMember;

    @Column(name = "min_response_time")
    private Long minResponseTime;

    @Column(name = "max_response_time")
    private Long maxResponseTime;

    @Column(name = "avg_response_time")
    private Long avgResponseTime;

    @Column(name = "clearing_member_count",nullable=false)
    private Integer clearingMemberCount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "response_status")
    private String responseStatus;

    // Getters and Setters

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public ClearingMember getClearingMember(){
        return clearingMember;
    }
    public void setClearingMember(ClearingMember clearingMember){
        this.clearingMember=clearingMember;
    }
    public Long getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(Long minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public Long getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(Long maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public Long getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(Long avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public Integer getClearingMemberCount() {
        return clearingMemberCount;
    }

    public void setClearingMemberCount(Integer clearingMemberCount) {
        this.clearingMemberCount = clearingMemberCount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
