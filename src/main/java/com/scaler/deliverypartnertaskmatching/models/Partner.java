package com.scaler.deliverypartnertaskmatching.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Partner extends BaseModel
{
    private String name;
    private double latitude;
    private double longitude;
    private boolean available; // status field to indicate whether a partner is available.
}

// PartnerLocationMicroservice: This will ingest partner location data.
// OrderMicroservice: This will handle the creation and management of tasks/orders.
// AllocationService: This will handle the matchmaking process.
