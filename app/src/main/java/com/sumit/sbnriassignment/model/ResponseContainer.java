package com.sumit.sbnriassignment.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class ResponseContainer {
    private int type;
    private List<RepoDb> repoDbList;

    public ResponseContainer(int type, List<RepoDb> repoDbList) {
        this.type = type;
        this.repoDbList = repoDbList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<RepoDb> getRepoDbList() {
        return repoDbList;
    }

    public void setRepoDbList(List<RepoDb> repoDbList) {
        this.repoDbList = repoDbList;
    }
}
