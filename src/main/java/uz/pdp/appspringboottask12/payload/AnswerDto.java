package uz.pdp.appspringboottask12.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {

    @NotNull(message = "text bo'sh bo'masligi kerak")
    private String text;

    @NotNull(message = "taskId bo'sh bo'masligi kerak")
    private Integer taskId;

    @NotNull(message = "userId bo'sh bo'masligi kerak")
    private Integer userId;

    @NotNull(message = "isCorrect bo'sh bo'masligi kerak")
    private boolean isCorrect;
}
