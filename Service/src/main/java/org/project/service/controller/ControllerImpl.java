package org.project.service.controller;

import org.project.service.service.BusinessLogicImpl;
import org.project.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ControllerImpl {
    @Autowired
    private BusinessLogicImpl businessLogic;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = businessLogic.getUsers();
        return ResponseEntity.ok(users);
    }
}
