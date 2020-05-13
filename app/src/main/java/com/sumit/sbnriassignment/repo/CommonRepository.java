package com.sumit.sbnriassignment.repo;

import com.sumit.sbnriassignment.model.Repo;
import com.sumit.sbnriassignment.repo.services.CommonApi;
import com.sumit.sbnriassignment.repo.services.RetrofitService;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * This class help to interact with service calls and return data back
 */
public class CommonRepository {


    private CommonApi commonApi;
    private final String TAG = CommonRepository.class.getSimpleName();

    public CommonRepository() {
        commonApi = RetrofitService.getInstance().create(CommonApi.class);
    }


    public Single<ArrayList<Repo>> getRepos(int page) {
        return commonApi.getRepos(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }


}
