package uz.pdp.appspringboottask12.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ExampleDto {

    @NotNull(message = "text bo'sh bo'lmasligi kerak")
    private String text;

    @NotNull(message = "taskId bo'sh bo'lmasligi kerak")
    private Integer taskId;
}
