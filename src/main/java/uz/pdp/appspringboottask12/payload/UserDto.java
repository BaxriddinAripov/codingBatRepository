package uz.pdp.appspringboottask12.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "email bo'sh bo'lmasligi kerak")
    private String email;

    @NotNull(message = "password bo'sh bo'lmasligi kerak")
    private String password;
}
