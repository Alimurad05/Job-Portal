package az.turing.jobportal.api;

import az.turing.jobportal.dto.LoginDto;
import az.turing.jobportal.dto.ResponseDto;
import az.turing.jobportal.dto.UserDTO;
import az.turing.jobportal.exception.JobPortalException;
import az.turing.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
@Validated
public class UserAPI {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException {
        userDTO = userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginDto> loginUser(@RequestBody @Valid LoginDto loginDTO) throws JobPortalException {
        loginDTO = userService.loginUser(loginDTO);
        return new ResponseEntity<>(loginDTO,HttpStatus.CREATED);
    }
    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDto> sendOtp(@PathVariable String email) throws Exception{
        userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDto("OTP sent successfully"),HttpStatus.CREATED);
    }
    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDto> sendOtp(@PathVariable String email,@PathVariable String otp)
            throws Exception{
        userService.verifyOtp(email,otp);
        return new ResponseEntity<>(new ResponseDto("OTP has been verified"),HttpStatus.OK);
    }

}
