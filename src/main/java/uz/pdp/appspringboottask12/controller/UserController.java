package uz.pdp.appspringboottask12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringboottask12.entity.User;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.UserDto;
import uz.pdp.appspringboottask12.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Barcha users larni qaytarish method
     * @return users
     */
    @GetMapping
    public ResponseEntity<List<User>> allUsers(){
        List<User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * ID orqali user ni qaytarish method
     * @param id Integer
     * @return user
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    /**
     * User yaratish method
     * @param userDto UserDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali userni tahrirlash
     * @param id Integer
     * @param userDto UserDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.editUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali userni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        ApiResponse apiResponse = userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
