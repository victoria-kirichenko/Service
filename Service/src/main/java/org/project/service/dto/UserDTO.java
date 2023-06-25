package org.project.service.dto;

public class UserDTO {
    private String id;
    private String name;
    private String password;
    public UserDTO(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserDTO() {
        this.id = null;
        this.name = null;
        this.password = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
