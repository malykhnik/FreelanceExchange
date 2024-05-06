package com.malykhnik.freelanceexchnge.service;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.model.PurchaseRequest;
import com.malykhnik.freelanceexchnge.model.User;

import java.util.List;
import java.util.Optional;

public interface PurchaseRequestService {
    void saveRequest(PurchaseRequest request);
    Optional<PurchaseRequest> findById(Long id);
    Optional<PurchaseRequest> findByOrderAndUsers(String order, String freelancer, String customer);
    List<PurchaseRequest> getAllRequestsByUserTo(Long id);
    List<PurchaseRequest> getAllRequestsByFreelancerWithStatus(String username, String status);
    List<PurchaseRequest> getRequestsFromCustomer(String username, String status);
}
