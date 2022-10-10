package uz.pdp.appspringboottask12.service;

import org.springframework.stereotype.Service;
import uz.pdp.appspringboottask12.entity.Answer;
import uz.pdp.appspringboottask12.entity.Task;
import uz.pdp.appspringboottask12.entity.User;
import uz.pdp.appspringboottask12.payload.AnswerDto;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.repository.AnswerRepository;
import uz.pdp.appspringboottask12.repository.TaskRepository;
import uz.pdp.appspringboottask12.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    final
    AnswerRepository answerRepository;
    final
    TaskRepository taskRepository;
    final
    UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    /**
     * Barcha answer larni qaytarish method
     * @return answers
     */
    public List<Answer> allAnswer(){
        return answerRepository.findAll();
    }

    /**
     * ID orqali answer ni qaytarish method
     * @param id Integer
     * @return answer
     */
    public Answer getAnswer(Integer id){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);
    }

    /**
     * Answer ni save qilish method
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    public ApiResponse addAnswer(AnswerDto answerDto){
        boolean exists = answerRepository.existsByText(answerDto.getText());
        if (exists)
            return new ApiResponse("Bunday text mavjud", false);
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday taskId mavjud emas", false);
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday userId mavjud emas", false);
        Answer answer = new Answer();
        answer.setText(answerDto.getText());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answer.setCorrect(answerDto.isCorrect());
        answerRepository.save(answer);
        return new ApiResponse("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    /**
     * ID orqali Answer ni tahrirlash method
     * @param id Integer
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    public ApiResponse editAnswer(Integer id, AnswerDto answerDto){
        boolean exists = answerRepository.existsByTextAndIdNot(answerDto.getText(), id);
        if (exists)
            return new ApiResponse("Bunday text va id mavjud", false);
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday taskId mavjud emas", false);
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday userId mavjud emas", false);
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Bunday answer masvjud emas", false);
        Answer answer = optionalAnswer.get();
        answer.setText(answerDto.getText());
        answer.setTask(optionalTask.get());
        answer.setTask(optionalTask.get());
        answer.setCorrect(answerDto.isCorrect());
        answerRepository.save(answer);
        return new ApiResponse("Ma'lumot tahrirlandi", true);
    }

    /**
     * ID orqali Answer ni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteAnswer(Integer id){
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }

}
