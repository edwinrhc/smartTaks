package com.erhc.smarttasks.repository;

import com.erhc.smarttasks.model.PaymentReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentReferenceRepository  extends JpaRepository<PaymentReference, Long> {

    Optional<PaymentReference> findByRefCode(String refCode);

}
