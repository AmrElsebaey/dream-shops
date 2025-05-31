package com.smartsolution.dreamshops.service.user;

import com.smartsolution.dreamshops.dto.UserDto;
import com.smartsolution.dreamshops.model.User;
import com.smartsolution.dreamshops.request.CreateUserRequest;
import com.smartsolution.dreamshops.request.UpdateUserRequest;

public interface IUserService {

    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long id);
    void deleteUser(Long id);
    User findUserById(Long id);

    UserDto convertToDto(User user);
}
