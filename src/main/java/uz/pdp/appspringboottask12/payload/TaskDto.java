package uz.pdp.appspringboottask12.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "text bo'sh bo'lmasligi kerak")
    private String text;

    @NotNull(message = "solution bo'sh bo'lmasligi kerak")
    private String solution;

    @NotNull(message = "hint bo'sh bo'lmasligi kerak")
    private String hint;

    @NotNull(message = "method bo'sh bo'lmasligi kerak")
    private String method;

    @NotNull(message = "answer bo'sh bo'lmasligi kerak")
    private boolean answer;

    @NotNull(message = "hasStar bo'sh bo'lmasligi kerak")
    private boolean hasStar;

    @NotNull(message = "languageId bo'sh bo'lmasligi kerak")
    private Integer languageId;
}
