package uz.pdp.appspringboottask12.service;

import org.springframework.stereotype.Service;
import uz.pdp.appspringboottask12.entity.Example;
import uz.pdp.appspringboottask12.entity.Task;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.ExampleDto;
import uz.pdp.appspringboottask12.repository.ExampleRepository;
import uz.pdp.appspringboottask12.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    final
    ExampleRepository exampleRepository;
    final
    TaskRepository taskRepository;

    public ExampleService(ExampleRepository exampleRepository, TaskRepository taskRepository) {
        this.exampleRepository = exampleRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Barcha Example larni qaytarish method
     * @return examples
     */
    public List<Example> allExample(){
        return exampleRepository.findAll();
    }

    /**
     * Example ni ID bo'yicha qaytarish method
     * @param id Integer
     * @return example
     */
    public Example getExample(Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(null);
    }

    /**
     * Example save qilish method
     * @param exampleDto ExampleDto
     * @return ApiResponse
     */
    public ApiResponse addExample(ExampleDto exampleDto){
        boolean exists = exampleRepository.existsByText(exampleDto.getText());
        if (exists)
            return new ApiResponse("Bunday text mavjud", false);
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday taskId mavjud emas", false);
        Example example = new Example();
        example.setText(exampleDto.getText());
        example.setTaskId(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    /**
     * ID orqali Example ni tahrirlash method
     * @param id Integer
     * @param exampleDto ExampleDto
     * @return ApiResponse
     */
    public ApiResponse editExample(Integer id, ExampleDto exampleDto){
        boolean exists = exampleRepository.existsByTextAndIdNot(exampleDto.getText(), id);
        if (exists)
            return new ApiResponse("Bunday text va id mavjud", false);
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday taskId mavjud emas", false);
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent())
            return new ApiResponse("Bunday example mavjud emas", false);
        Example example = optionalExample.get();
        example.setText(exampleDto.getText());
        example.setTaskId(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Ma'lumot tahrirlandi", true);
    }

    /**
     * ID orqali Example ni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteExample(Integer id){
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }
}
