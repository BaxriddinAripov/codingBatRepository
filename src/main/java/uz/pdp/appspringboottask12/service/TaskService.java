package uz.pdp.appspringboottask12.service;

import org.springframework.stereotype.Service;
import uz.pdp.appspringboottask12.entity.Language;
import uz.pdp.appspringboottask12.entity.Task;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.TaskDto;
import uz.pdp.appspringboottask12.repository.LanguageRepository;
import uz.pdp.appspringboottask12.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    final
    TaskRepository taskRepository;
    final
    LanguageRepository languageRepository;

    public TaskService(TaskRepository taskRepository, LanguageRepository languageRepository) {
        this.taskRepository = taskRepository;
        this.languageRepository = languageRepository;
    }

    /**
     * Barcha task larni qaytarish method
     * @return tasks
     */
    public List<Task> allTasks(){
        return taskRepository.findAll();
    }

    /**
     * ID orqali task ni qaytarish method
     * @param id Integer
     * @return task
     */
    public Task getTask(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    /**
     * Task ni save qilish method
     * @param taskDto TaskDto
     * @return ApiResponse
     */
    public ApiResponse addTask(TaskDto taskDto){
        boolean exists = taskRepository.existsByName(taskDto.getName());
        if (exists)
            return new ApiResponse("Bunday name mavjud", false);
        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday languageId mavjud emas", false);
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setSolution(taskDto.getSolution());
        task.setHint(taskDto.getHint());
        task.setMethod(taskDto.getMethod());
        task.setAnswer(taskDto.isAnswer());
        task.setHasStar(taskDto.isHasStar());
        task.setLanguageId(optionalLanguage.get());
        taskRepository.save(task);
        return new ApiResponse("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    /**
     * ID orqali Task ni tahrirlash method
     * @param id INteger
     * @param taskDto TaskDto
     * @return ApiResponse
     */
    public ApiResponse editTask(Integer id, TaskDto taskDto){
        boolean exists = taskRepository.existsByNameAndIdNot(taskDto.getName(), id);
        if (exists)
            return new ApiResponse("Bunday name va id mavjud", false);
        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday languageId mavjud emas", false);
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday taskId mavjud emas", false);
        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setSolution(taskDto.getSolution());
        task.setHint(taskDto.getHint());
        task.setMethod(taskDto.getMethod());
        task.setAnswer(taskDto.isAnswer());
        task.setHasStar(taskDto.isHasStar());
        task.setLanguageId(optionalLanguage.get());
        taskRepository.save(task);
        return new ApiResponse("Ma'lumot tahrirlandi", true);
    }

    /**
     * ID orqali Task ni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteTask(Integer id){
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }
}
