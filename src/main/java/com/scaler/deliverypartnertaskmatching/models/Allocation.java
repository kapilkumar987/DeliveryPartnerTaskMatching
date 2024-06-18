package com.scaler.deliverypartnertaskmatching.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Allocation extends BaseModel
{
    private Long partnerId;
    private Long taskId;
}
