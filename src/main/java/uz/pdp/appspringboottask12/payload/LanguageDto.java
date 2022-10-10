package uz.pdp.appspringboottask12.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LanguageDto {

    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;
}
