package com.smartsolution.dreamshops.service.user;

import com.smartsolution.dreamshops.exceptions.AlreadyExistsException;
import com.smartsolution.dreamshops.exceptions.UserNotFoundException;
import com.smartsolution.dreamshops.model.User;
import com.smartsolution.dreamshops.request.CreateUserRequest;
import com.smartsolution.dreamshops.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;


    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword()); // Consider hashing the password
                    return userRepository.save(user);
                }).orElseThrow(() -> new AlreadyExistsException("User with email " + request.getEmail() + " already exists."));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(userRepository::delete,
                        () -> { throw new UserNotFoundException("User not found with id: " + id); });
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}
