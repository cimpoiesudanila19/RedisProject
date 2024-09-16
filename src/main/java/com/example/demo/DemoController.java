package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class DemoController {

    @Autowired
    private TaskService taskService;

    /* POST
    http://localhost:8080/tasks
    Body -> x-www-form-urlencoded
    key -> id, description
    value -> 1, zxc
     */
    @PostMapping
    public void addTask(@RequestParam String id, @RequestParam String description) {
        taskService.addTask(id, description);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable String id) {
        return taskService.getTask(id);
    }
}
C:\Users\drc\Desktop\project\cache