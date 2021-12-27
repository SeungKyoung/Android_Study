package com.example.new01_intent;

import java.io.Serializable;

public class TestDTO implements Serializable {
    private String text;
    private int num;

    public TestDTO(String text, int num) {
        this.text = text;
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
