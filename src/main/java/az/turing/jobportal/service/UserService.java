package az.turing.jobportal.service;

import az.turing.jobportal.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDTO registerUser(UserDTO userDTO);
}
