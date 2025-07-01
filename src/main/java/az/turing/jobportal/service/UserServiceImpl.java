package az.turing.jobportal.service;

import az.turing.jobportal.dto.UserDTO;
import az.turing.jobportal.entity.User;
import az.turing.jobportal.exception.JobPortalException;
import az.turing.jobportal.repo.UserRepository;
import az.turing.jobportal.utility.Utilities;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        userDTO.setId(Utilities.getNextSequence("users"));
        User user=userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDto();
    }
}
