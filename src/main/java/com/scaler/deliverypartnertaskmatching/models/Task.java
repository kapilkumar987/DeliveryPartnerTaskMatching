package com.scaler.deliverypartnertaskmatching.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Task extends BaseModel
{
    // Tasks | Orders

    private String description;
    private double latitude;
    private double longitude;
    private int priority; // Task Prioritization: Add a priority field to tasks and sort tasks by priority.
}
