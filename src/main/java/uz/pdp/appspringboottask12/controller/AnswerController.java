package uz.pdp.appspringboottask12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringboottask12.entity.Answer;
import uz.pdp.appspringboottask12.payload.AnswerDto;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.service.AnswerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    final
    AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * Barcha answer larni qaytarish method
     * @return answers
     */
    @GetMapping
    public ResponseEntity<List<Answer>> allAnswer(){
        List<Answer> answers = answerService.allAnswer();
        return ResponseEntity.ok(answers);
    }

    /**
     * ID orqali answer ni qaytarish method
     * @param id Integer
     * @return answer
     */
    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable Integer id){
        Answer answer = answerService.getAnswer(id);
        return ResponseEntity.ok(answer);
    }

    /**
     * Answer ni save qilish method
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addAnswer(@Valid @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali Answer ni tahrirlash method
     * @param id Integer
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAnswer(@PathVariable Integer id, @Valid @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.editAnswer(id, answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali Answer ni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAnswer(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.deleteAnswer(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
