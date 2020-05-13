package com.sumit.sbnriassignment.model;

import com.google.gson.annotations.SerializedName;

public class Permission {

    @SerializedName("admin")
    private boolean admin;
    @SerializedName("push")
    private boolean push;
    @SerializedName("pull")
    private boolean pull;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public boolean isPull() {
        return pull;
    }

    public void setPull(boolean pull) {
        this.pull = pull;
    }
}
