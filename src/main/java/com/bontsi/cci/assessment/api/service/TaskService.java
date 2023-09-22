package com.bontsi.cci.assessment.api.service;

import com.bontsi.cci.assessment.api.model.Task;
import com.bontsi.cci.assessment.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public  Task updateTask(Long id, Task taskDetails){
        Task existing = taskRepository.findById(id).get();
        existing.setName(taskDetails.getName());
        existing.setDescription(taskDetails.getDescription());
        existing.setDate_time(taskDetails.getDate_time());
        return taskRepository.save(existing);
    }
    public Task getTaskById(Long id){
        return taskRepository.findById(id).get();
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }


}
