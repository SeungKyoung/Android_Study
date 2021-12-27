package com.example.ex10_intentresult;

import java.io.Serializable;

public class testDTO implements Serializable {
    String field1;
    int field2;
    double fiedl3;
    //intent가 객체를 보낼때는 반드시 Serializable을 해줘야하는
    /* alt + insert*/

    public testDTO(String field1, int field2, double fiedl3) {
        this.field1 = field1;
        this.field2 = field2;
        this.fiedl3 = fiedl3;
    }

    public String getField1() {
        return field1;
    }

    public int getField2() {
        return field2;
    }

    public double getFiedl3() {
        return fiedl3;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public void setFiedl3(double fiedl3) {
        this.fiedl3 = fiedl3;
    }
}
