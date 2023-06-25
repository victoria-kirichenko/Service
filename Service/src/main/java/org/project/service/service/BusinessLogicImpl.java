package org.project.service.service;

import org.project.service.dto.UserDTO;
import org.project.service.models.User;
import org.project.service.repository.RepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessLogicImpl {
    @Autowired
    private RepositoryImpl userRepository;

    public List<User> getUsers() {
        List<UserDTO> usersDTO = userRepository.getUsers();
        return mapToUser(usersDTO);
    }

    private static List<User> mapToUser(List<UserDTO> usersDTO) {
        List<User> userList = new ArrayList<>();
        usersDTO.stream()
                .forEach(userDTO -> {
                    User user = new User();
                    user.setId(userDTO.getId());
                    user.setName(userDTO.getName());
                    user.setPassword(userDTO.getPassword());
                    userList.add(user);
                });
        return userList;
    }
}
