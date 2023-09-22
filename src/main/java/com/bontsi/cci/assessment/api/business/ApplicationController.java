package com.bontsi.cci.assessment.api.business;

import com.bontsi.cci.assessment.api.model.Task;
import com.bontsi.cci.assessment.api.model.User;
import com.bontsi.cci.assessment.api.service.TaskService;
import com.bontsi.cci.assessment.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class ApplicationController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            User userOutput = userService.createUser(user);
            return new ResponseEntity<>(userOutput, HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable(value = "id")Long id,@RequestBody User userDetails){
        User user =userService.updateUser(id,userDetails);
        if (user!= null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            List<User> users = new ArrayList<User>();
            userService.getUsers().forEach(users::add);
            if (users.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(users,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable(value = "id")Long id){
        try{
            return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(value = "id")Long id){
        try{
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //Task Endpoints start
    @RequestMapping(value = "/users/{id}/tasks",method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@PathVariable(value = "id") long id,@RequestBody Task taskDetails){
        try{
            taskDetails.setUserId(id);
            return new ResponseEntity<>(taskService.createTask(taskDetails),HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(value = "/users/{user_id}/tasks/{task_id}",method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@PathVariable(value = "user_id")Long userId,@PathVariable(value = "task_id")Long taskId,@RequestBody Task taskDetails){
       try{
           taskDetails.setUserId(userId);
           taskDetails.setId(taskId);
           return new ResponseEntity<>(taskService.updateTask(taskId,taskDetails),HttpStatus.OK);
       }catch(Exception e){
           return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @RequestMapping(value = "/users/{user_id}/tasks/{task_id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable(value = "user_id")Long userId,@PathVariable(value = "task_id")Long taskId,@RequestBody Task taskDetails){
        try{
            taskService.deleteTask(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/users/{user_id}/tasks/{task_id}")
    public ResponseEntity<Task> getTask(@PathVariable(value = "user_id")Long userId,@PathVariable(value = "task_id")Long taskId,@RequestBody Task taskDetails){
        try{
            List<Task> taskList =  taskService.getAllTasks().stream()
                    .filter(user-> user.getUserId()==userId && user.getId()==taskId)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(taskList.get(0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/users/{user_id}/tasks",method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable(value = "user_id")Long id) {
        try{
        List<Task> taskList = taskService.getAllTasks().stream()
                .filter(user -> user.getUserId()==id)
                .collect(Collectors.toList());
        if (taskList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}
