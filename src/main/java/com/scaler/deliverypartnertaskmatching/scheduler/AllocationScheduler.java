package com.scaler.deliverypartnertaskmatching.scheduler;

import com.scaler.deliverypartnertaskmatching.models.Allocation;
import com.scaler.deliverypartnertaskmatching.models.Partner;
import com.scaler.deliverypartnertaskmatching.models.Task;
import com.scaler.deliverypartnertaskmatching.repositories.AllocationRepository;
import com.scaler.deliverypartnertaskmatching.repositories.PartnerRepository;
import com.scaler.deliverypartnertaskmatching.repositories.TaskRepository;
import com.scaler.deliverypartnertaskmatching.services.MatchMakingService;
import com.scaler.deliverypartnertaskmatching.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AllocationScheduler
{
    private final TaskRepository taskRepository;
    private final PartnerRepository partnerRepository;
    private final AllocationRepository allocationRepository;
    private final MatchMakingService matchMakingService;

    private final RedisService redisService;


    @Autowired
    public AllocationScheduler(TaskRepository taskRepository, PartnerRepository partnerRepository, AllocationRepository allocationRepository, MatchMakingService matchMakingService, RedisService redisService)
    {
        this.taskRepository = taskRepository;
        this.partnerRepository = partnerRepository;
        this.allocationRepository = allocationRepository;
        this.matchMakingService = matchMakingService;
        this.redisService = redisService;
    }

    @Scheduled(cron = "0 0/1 * * * ?") // Every minute
    public void allocateTasks()
    {
        List<Task> tasks = redisService.getAllTasks().stream()
                .map(obj -> (Task) obj)
                .sorted(Comparator.comparingInt(Task::getPriority).reversed())
                .collect(Collectors.toList());

        List<Partner> partnerList = redisService.getAllPartners().stream()
                .map(obj -> (Partner) obj)
                .collect(Collectors.toList());

        // Sort tasks by priority (higher priority first)
        // tasks.sort(Comparator.comparingInt(Task::getPriority).reversed());

        for (Task task: tasks)
        {
            Allocation allocation = this.matchMakingService.allocateTaskToPartner(task, partnerList);

            if(allocation != null)
            {
                this.allocationRepository.save(allocation);
            }
        }
    }
}
