package com.technical.api_metrics_tracker.service;

import com.technical.api_metrics_tracker.model.ClearingMember;
import com.technical.api_metrics_tracker.model.ClearingMemberMetrics;
import com.technical.api_metrics_tracker.model.TransactionMetrics;
import com.technical.api_metrics_tracker.repository.ClearingMemberRepository;
import com.technical.api_metrics_tracker.repository.ClearingMemberMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClearingMemberService {
    private final ClearingMemberRepository clearingMemberRepository;
    private final ClearingMemberMetricsRepository clearingMemberMetricsRepository;

    @Autowired
    public ClearingMemberService(ClearingMemberRepository clearingMemberRepository,
                                 ClearingMemberMetricsRepository clearingMemberMetricsRepository) {
        this.clearingMemberRepository = clearingMemberRepository;
        this.clearingMemberMetricsRepository = clearingMemberMetricsRepository;
    }

    /**
     * Logs a new Clearing Member and updates metrics.
     */
    public void logClearingMember(String memberName, String memberType, long startTime, int statusCode) {
        try {
            // Save ClearingMember
            ClearingMember clearingMember = new ClearingMember();
            clearingMember.setMemberName(memberName);
            clearingMember.setMemberType(memberType);
            clearingMember.setRegistrationDate(LocalDateTime.now());
            clearingMember = clearingMemberRepository.save(clearingMember);  // Saving ClearingMember object

            // Calculate and save ClearingMemberMetrics
            ClearingMemberMetrics clearingMemberMetrics = calculateMetrics(clearingMember, startTime, statusCode);

            // Associate the ClearingMember object with ClearingMemberMetrics
            // Ensure relationship is set

            clearingMemberMetricsRepository.save(clearingMemberMetrics);  // Save the metrics

        } catch (Exception e) {
            throw new RuntimeException("Error while logging clearing member", e);
        }
    }

    /*Calculates metrics for a given ClearingMember.*/
    private ClearingMemberMetrics calculateMetrics(ClearingMember clearingMember, long startTime, int statusCode) {
        ClearingMemberMetrics metrics =new ClearingMemberMetrics();

        long responseTime = System.currentTimeMillis() - startTime;
        metrics.setMinResponseTime(metrics.getMinResponseTime() == null ? responseTime : Math.min(metrics.getMinResponseTime(), responseTime));
        metrics.setMaxResponseTime(metrics.getMaxResponseTime() == null ? responseTime : Math.max(metrics.getMaxResponseTime(), responseTime));

        if (metrics.getClearingMemberCount() == null) {
            metrics.setClearingMemberCount(1);
            metrics.setAvgResponseTime(responseTime);
        } else {
            int newCount = metrics.getClearingMemberCount() + 1;
            long totalResponseTime = metrics.getAvgResponseTime() * metrics.getClearingMemberCount() + responseTime;
            metrics.setClearingMemberCount(newCount);
            metrics.setAvgResponseTime(totalResponseTime / newCount);
        }

        // Set the response status as a formatted string
        String statusText = HttpStatus.valueOf(statusCode).getReasonPhrase();
        metrics.setResponseStatus(statusCode + " " + statusText);

        // Set other fields
        metrics.setClearingMember(clearingMember);
        metrics.setTimestamp(LocalDateTime.now());

        return metrics;  // Return updated metrics
    }
    public String getClearingMembersDetailsById(long memberId) {
        return clearingMemberRepository.findById(memberId)
                .map(clearingMember -> "member ID: " + clearingMember.getMemberId() +
                        "\nMember Type: " + clearingMember.getMemberType() +
                        "\nMember Name: " + clearingMember.getMemberName() +
                        "\nRegistration Date: " + clearingMember.getRegistrationDate())
                .orElse("Clearing Member not found");
    }
}


