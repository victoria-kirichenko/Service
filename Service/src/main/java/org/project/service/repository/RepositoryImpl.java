package org.project.service.repository;

import org.project.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Repository
public class RepositoryImpl {
    private static final String JSON_FILE_PATH = "classpath:json/data.json";
    @Autowired
    private ResourceLoader resourceLoader;
    public List<UserDTO> getUsers() {
        try {
            Resource resource = resourceLoader.getResource(JSON_FILE_PATH);
            File jsonFile = resource.getFile();

            ObjectMapper objectMapper = new ObjectMapper();
            List<UserDTO> userList = objectMapper.readValue(jsonFile, new TypeReference<List<UserDTO>>() {});
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
