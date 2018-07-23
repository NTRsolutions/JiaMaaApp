package com.example.dell.jaimaaapp.modal;

public class ImageList {

    private String downloadURL;

    public ImageList() {
    }

    public ImageList(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}
