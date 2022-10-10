package uz.pdp.appspringboottask12.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class CategoryDto {

    @NotNull(message = "name bo'sh bo'masligi kerak")
    private String name;

    @NotNull(message = "discreption bo'sh bo'masligi kerak")
    private String discreption;

    @NotNull(message = "languageId bo'sh bo'masligi kerak")
    private Set<Integer> languageId;
}
