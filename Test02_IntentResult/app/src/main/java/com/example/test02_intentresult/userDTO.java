package com.example.test02_intentresult;

import java.io.Serializable;

public class userDTO implements Serializable {
    private String id, pw, name, addr, nick;
    private int age;

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public String getNick() {
        return nick;
    }

    public int getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
