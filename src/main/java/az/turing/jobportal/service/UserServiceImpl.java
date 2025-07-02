package az.turing.jobportal.service;

import az.turing.jobportal.dto.LoginDto;
import az.turing.jobportal.dto.UserDTO;
import az.turing.jobportal.entity.OTP;
import az.turing.jobportal.entity.User;
import az.turing.jobportal.exception.JobPortalException;
import az.turing.jobportal.repo.UserRepository;
import az.turing.jobportal.utility.Utilities;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user=userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDto();
    }
    @Override
    public LoginDto loginUser(LoginDto loginDTO) throws JobPortalException {
        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new JobPortalException("User not found"));
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            throw new JobPortalException("Invalid credentials");
        }
        return new LoginDto(user.getEmail(),user.getPassword());
    }
    @Override
    public Boolean sendOtp(String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new JobPortalException("User not found"));
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(email);
        message.setSubject("Your OTP Code");
        String otp = Utilities.generateOtp();
        OTP otpEntity = new OTP(user.getId(),email,otp, LocalDateTime.now());
        // You might want to save the OTP in the database or cache for verification later
    }

}
