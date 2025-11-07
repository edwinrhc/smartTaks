package com.erhc.smarttasks.repository;

import com.erhc.smarttasks.model.ComprobantePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobantePagoRepository extends JpaRepository<ComprobantePago, Long> {
}
