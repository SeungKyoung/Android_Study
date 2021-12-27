package com.example.myprojectx.DTO;

public class MemberDTO {
    private String id, passwd, name, phonenumber, address, imgpath;

    // 로그인시 암호없이 멤버정보를 가져올 때
    public MemberDTO(String id, String name, String phonenumber, String address, String imgpath) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.address = address;
        this.imgpath = imgpath;
    }

    // 회원가입할때 모든 정보를 저장하기 위해
    public MemberDTO(String id, String passwd, String name, String phonenumber, String address, String imgpath) {
        this.id = id;
        this.passwd = passwd;
        this.name = name;
        this.phonenumber = phonenumber;
        this.address = address;
        this.imgpath = imgpath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}
