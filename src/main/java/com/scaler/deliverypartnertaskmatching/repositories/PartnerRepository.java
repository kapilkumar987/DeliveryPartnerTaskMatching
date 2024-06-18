package com.scaler.deliverypartnertaskmatching.repositories;

import com.scaler.deliverypartnertaskmatching.models.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>
{

}
