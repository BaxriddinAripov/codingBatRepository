package uz.pdp.appspringboottask12.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appspringboottask12.entity.User;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.UserDto;
import uz.pdp.appspringboottask12.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Barcha users larni qaytarish method
     * @return users
     */
    public List<User> allUsers(){
        return userRepository.findAll();
    }

    /**
     * ID orqali user ni qaytarish method
     * @param id Integer
     * @return user
     */
    public User getUser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    /**
     * User yaratish method
     * @param userDto UserDto
     * @return ApiResponse
     */
    public ApiResponse addUser(UserDto userDto){
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists)
            return new ApiResponse("Bunday email mavjud", false);
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    /**
     * ID orqali userni tahrirlash
     * @param id Integer
     * @param userDto UserDto
     * @return ApiResponse
     */
    public ApiResponse editUser(Integer id, UserDto userDto){
        boolean exists = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
        if (exists)
            return new ApiResponse("Bunday email va id mavjud", false);
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday user mavjud emas", false);
        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("Malumot muvaffaqiyatli o'zgartirildi", true);
    }

    /**
     * ID orqali userni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteUser(Integer id){
        try {
            userRepository.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatplik!!!", false);
        }
    }
}
