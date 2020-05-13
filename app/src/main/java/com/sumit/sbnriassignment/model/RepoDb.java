package com.sumit.sbnriassignment.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "repos")
public class RepoDb {

    @PrimaryKey()
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "desc")
    private String desc;
    @ColumnInfo(name = "permission")
    private String permission;
    @ColumnInfo(name = "license")
    private String license;
    @ColumnInfo(name = "issue_count")
    private String issueCount;


    public RepoDb(Repo repo) {
        this.id = repo.getId();
        this.name = repo.getName();
        this.desc = repo.getDescription();

        String temp = "";
        if (repo.getPermission().isAdmin()) {
            temp = temp + "admin ";
        }
        if (repo.getPermission().isPull()) {
            temp = temp + "pull ";
        }
        if (repo.getPermission().isPush()) {
            temp = temp + "push ";
        }

        this.permission = temp;
        if (repo.getLicense() != null) {
            this.license = repo.getLicense().getName();
        }
        this.issueCount = "" + repo.getOpenIssueCount();

    }


    public RepoDb(String name, String desc, String permission, String license, String issueCount) {
        this.name = name;
        this.desc = desc;
        this.permission = permission;
        this.license = license;
        this.issueCount = issueCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPermission() {
        return permission;
    }

    public String getLicense() {
        return license;
    }

    public String getIssueCount() {
        return issueCount;
    }
}
