package com.spitslide.celebrityrecognition;


import java.util.List;

public class Match {
    private List<String> url;
    private String name;
    private String value;

    public List<String> getUrls() {
        return url;
    }

    public void setUrls(List<String> url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
