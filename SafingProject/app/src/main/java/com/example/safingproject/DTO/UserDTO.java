package com.example.safingproject.DTO;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String id, pw, name;
    private int birth;

    public UserDTO() { }

    public UserDTO(String id, String pw, String name, int birth) {
        super();
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBirth() {
        return birth;
    }
    public void setBirth(int birth) {
        this.birth = birth;
    }
}
