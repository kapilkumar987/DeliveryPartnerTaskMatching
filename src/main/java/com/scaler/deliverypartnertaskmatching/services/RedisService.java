package com.scaler.deliverypartnertaskmatching.services;

import com.scaler.deliverypartnertaskmatching.models.Partner;
import com.scaler.deliverypartnertaskmatching.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedisService
{
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String PARTNER_KEY = "Partner";
    private static final String TASK_KEY = "Task";

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate)
    {
        this.redisTemplate = redisTemplate;
    }

    public void savePartner(Partner partner) {
        redisTemplate.opsForHash().put(PARTNER_KEY, partner.getId(), partner);
    }

    public Optional<Partner> getPartnerById(Long id) {
        return Optional.ofNullable((Partner) redisTemplate.opsForHash().get(PARTNER_KEY, id));
    }

    public List<Object> getAllPartners() {
        return redisTemplate.opsForHash().values(PARTNER_KEY);
    }

    public void saveTask(Task task) {
        redisTemplate.opsForHash().put(TASK_KEY, task.getId(), task);
    }

    public List<Object> getAllTasks() {
        return redisTemplate.opsForHash().values(TASK_KEY);
    }
}
