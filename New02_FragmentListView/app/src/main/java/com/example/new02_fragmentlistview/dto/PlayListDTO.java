package com.example.new02_fragmentlistview.dto;

public class PlayListDTO {
    private int imageId;
    private String subject, content;

    public PlayListDTO(int imageId, String subject, String content) {
        this.imageId = imageId;
        this.subject = subject;
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
