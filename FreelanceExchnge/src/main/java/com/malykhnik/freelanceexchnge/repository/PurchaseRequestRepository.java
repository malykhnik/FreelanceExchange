package com.malykhnik.freelanceexchnge.repository;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.model.PurchaseRequest;
import com.malykhnik.freelanceexchnge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {
    Optional<PurchaseRequest> findById(Long id);

    @Query("SELECT pr FROM PurchaseRequest pr " +
            "WHERE pr.order.id = :orderName " +
            "AND pr.userFrom = :userFrom " +
            "AND pr.userTo = :userTo")
    Optional<PurchaseRequest> findByOrderNameAndUsers(
            @Param("orderName") String orderName,
            @Param("userFrom") String userFrom,
            @Param("userTo") String userTo);

    @Query("SELECT pr FROM PurchaseRequest pr WHERE pr.userTo = :id")
    List<PurchaseRequest> getAllRequestsByUserTo(@Param("id") Long id);
}
