package com.scaler.deliverypartnertaskmatching.repositories;

import com.scaler.deliverypartnertaskmatching.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{

}
