package com.zht.iclass.model;

public class Class{
    private Integer id;
    private String username;
    private String classname;
    private Integer outin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getOutin() {
        return outin;
    }

    public void setOutin(Integer outin) {
        this.outin = outin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
