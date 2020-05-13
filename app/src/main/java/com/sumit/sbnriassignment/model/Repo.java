package com.sumit.sbnriassignment.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("open_issues_count")
    private int openIssueCount;
    @SerializedName("license")
    private License license;
    @SerializedName("permissions")
    private Permission permission;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOpenIssueCount() {
        return openIssueCount;
    }

    public void setOpenIssueCount(int openIssueCount) {
        this.openIssueCount = openIssueCount;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
