package az.turing.jobportal.service;

import az.turing.jobportal.dto.UserDTO;
import az.turing.jobportal.entity.User;
import az.turing.jobportal.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        User user=userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDto();
    }
}
