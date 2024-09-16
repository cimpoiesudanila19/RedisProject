package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TaskService {

    private static final String TASK_PREFIX = "task:";

    @Autowired
    private RedisTemplate<String, Task> redisTemplate;

    public void addTask(String id, String description) {
        Task task = new Task(id, description);
        redisTemplate.opsForValue().set(TASK_PREFIX + id, task, 10, TimeUnit.MINUTES);
        System.out.println("Task added: " + task);
    }

    public Task getTask(String id) {
        Task task = redisTemplate.opsForValue().get(TASK_PREFIX + id);
        if (task != null) {
            System.out.println("Task retrieved: " + task);
        } else {
            System.out.println("Task not found with id: " + id);
        }
        return task;
    }
}
