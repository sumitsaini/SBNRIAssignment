package com.sumit.sbnriassignment.repo.services;

import com.sumit.sbnriassignment.model.Repo;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sumitsaini
 */

public interface CommonApi {

    @GET("/orgs/octokit/repos?per_page=10")
    Single<ArrayList<Repo>> getRepos(@Query("page") int page);




}
