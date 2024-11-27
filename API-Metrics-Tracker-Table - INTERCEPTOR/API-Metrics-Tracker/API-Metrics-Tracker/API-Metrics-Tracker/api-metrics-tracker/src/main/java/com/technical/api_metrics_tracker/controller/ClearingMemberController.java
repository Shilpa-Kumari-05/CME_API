package com.technical.api_metrics_tracker.controller;

import com.technical.api_metrics_tracker.model.ClearingMember;
import jakarta.servlet.http.HttpServletRequest;
import com.technical.api_metrics_tracker.service.ClearingMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clearing-members")
public class ClearingMemberController {

    private final ClearingMemberService clearingMemberService;

    @Autowired
    public ClearingMemberController(ClearingMemberService clearingMemberService) {
        this.clearingMemberService = clearingMemberService;
    }


     /* API to create a new Clearing Member*/

    @PostMapping("/log")
    public ResponseEntity<String> logClearingMember(@RequestBody ClearingMemberDTO request,
                                                    HttpServletRequest servletRequest) {
        // Get the start time from the interceptor
        long startTime = (long) servletRequest.getAttribute("startTime");

        try {
            // Extract Clearing Member details from request
            String memberName = request.getMemberName();
            String memberType = request.getMemberType();

            // Log clearing member and metrics
            clearingMemberService.logClearingMember(memberName, memberType, startTime, HttpStatus.CREATED.value());

            return new ResponseEntity<>("Clearing Member and related details logged successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while logging the Clearing Member details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/clearing-member/{memberId}")
    public ResponseEntity<String> getClearingMembersDetails(@PathVariable long memberId) {
        // Call the service method to fetch transaction details by transactionId
        String clearingMembersDetails = clearingMemberService.getClearingMembersDetailsById(memberId);

        // If transaction details are found, return them in string format
        if (clearingMembersDetails != null) {
            return new ResponseEntity<>(clearingMembersDetails,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Member ID not found", HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }
}


