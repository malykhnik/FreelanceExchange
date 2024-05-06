package com.malykhnik.freelanceexchnge.service.impls;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.model.PurchaseRequest;
import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.repository.PurchaseRequestRepository;
import com.malykhnik.freelanceexchnge.service.PurchaseRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseRequestServiceImpl implements PurchaseRequestService {
    private final PurchaseRequestRepository requestRepository;
    @Override
    public void saveRequest(PurchaseRequest request) {
        requestRepository.save(request);
    }

    @Override
    public Optional<PurchaseRequest> findById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public Optional<PurchaseRequest> findByOrderAndUsers(String order, String freelancer, String customer) {
        return requestRepository.findByOrderNameAndUsers(order, freelancer, customer);
    }

    @Override
    public List<PurchaseRequest> getAllRequestsByUserTo(Long id) {
        return requestRepository.getAllRequestsByUserTo(id);
    }

    @Override
    public List<PurchaseRequest> getAllRequestsByFreelancerWithStatus(String username, String status) {
        return requestRepository.getAllRequestsByFreelancerWithStatus(username, status);
    }

    @Override
    public List<PurchaseRequest> getRequestsFromCustomer(String username, String status) {
        return requestRepository.getRequestsFromCustomer(username, status);
    }
}
