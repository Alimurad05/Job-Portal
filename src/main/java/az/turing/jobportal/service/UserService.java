package az.turing.jobportal.service;

import az.turing.jobportal.dto.LoginDto;
import az.turing.jobportal.dto.UserDTO;
import az.turing.jobportal.exception.JobPortalException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;
    public LoginDto loginUser(LoginDto loginDTO) throws JobPortalException;
    public Boolean sendOtp(String email) throws Exception;
}
