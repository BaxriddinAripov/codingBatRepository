package uz.pdp.appspringboottask12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringboottask12.entity.Example;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.ExampleDto;
import uz.pdp.appspringboottask12.service.ExampleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    final
    ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    /**
     * Barcha Example larni qaytarish method
     * @return examples
     */
    @GetMapping
    public ResponseEntity<List<Example>> allExample(){
        List<Example> examples = exampleService.allExample();
        return ResponseEntity.ok(examples);
    }

    /**
     * Example ni ID bo'yicha qaytarish method
     * @param id Integer
     * @return example
     */
    @GetMapping("/{id}")
    public ResponseEntity<Example> getExample(@PathVariable Integer id){
        Example example = exampleService.getExample(id);
        return ResponseEntity.ok(example);
    }

    /**
     * Example save qilish method
     * @param exampleDto ExampleDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addExample(@Valid @RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.addExample(exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali Example ni tahrirlash method
     * @param id Integer
     * @param exampleDto ExampleDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editExample(@PathVariable Integer id, @Valid @RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.editExample(id, exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali Example ni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteExample(@PathVariable Integer id){
        ApiResponse apiResponse = exampleService.deleteExample(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
