package com.example.model;

public class Page {

    private String name;

    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Page{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
