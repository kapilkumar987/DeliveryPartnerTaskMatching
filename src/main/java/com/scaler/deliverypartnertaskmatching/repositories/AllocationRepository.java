package com.scaler.deliverypartnertaskmatching.repositories;

import com.scaler.deliverypartnertaskmatching.models.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long>
{

}
