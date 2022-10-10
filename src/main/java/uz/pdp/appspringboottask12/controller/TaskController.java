package uz.pdp.appspringboottask12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringboottask12.entity.Task;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.TaskDto;
import uz.pdp.appspringboottask12.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    final
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Barcha task larni qaytarish method
     * @return tasks
     */
    @GetMapping
    public ResponseEntity<List<Task>> allTasks(){
        List<Task> tasks = taskService.allTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * ID orqali task ni qaytarish method
     * @param id Integer
     * @return task
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id){
        Task task = taskService.getTask(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Task ni save qilish method
     * @param taskDto TaskDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addTask(@Valid @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.addTask(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali Task ni tahrirlash method
     * @param id INteger
     * @param taskDto TaskDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editTask(@PathVariable Integer id, @Valid @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.editTask(id, taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali Task ni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer id){
        ApiResponse apiResponse = taskService.deleteTask(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }


}
