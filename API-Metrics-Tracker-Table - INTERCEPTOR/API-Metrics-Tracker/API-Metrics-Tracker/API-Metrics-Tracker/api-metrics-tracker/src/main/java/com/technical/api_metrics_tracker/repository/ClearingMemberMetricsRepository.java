//package com.technical.api_metrics_tracker.repository;
//
//import com.technical.api_metrics_tracker.model.ClearingMemberMetrics;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import java.util.List;
//
//@Repository
//public interface ClearingMemberMetricsRepository extends JpaRepository<ClearingMemberMetrics, Long> {
//
//   // Find metrics by Clearing Member ID
//   //    List<ClearingMemberMetrics> findByClearingMember_ClearingMemberId(Long clearingMemberId);
//    // Optional: Find by response status (if needed)
////    List<ClearingMemberMetrics> findByResponseStatus(String responseStatus);
//}
package com.technical.api_metrics_tracker.repository;

import com.technical.api_metrics_tracker.model.ClearingMemberMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClearingMemberMetricsRepository extends JpaRepository<ClearingMemberMetrics, Long> {
    // Custom query methods (if necessary) can be added here
}


