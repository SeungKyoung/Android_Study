package com.example.new02_fragmentlistview.dto;

import androidx.fragment.app.Fragment;

public class FragmentDTO {
    private Fragment fragment;
    private String msg;

    public FragmentDTO(Fragment fragment, String msg) {
        this.fragment = fragment;
        this.msg = msg;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
