package com.technical.api_metrics_tracker.repository;

import com.technical.api_metrics_tracker.model.ClearingMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClearingMemberRepository extends JpaRepository<ClearingMember, Long> {

    // Custom query methods can be added here if needed
}

