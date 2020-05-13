package com.sumit.sbnriassignment.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.sumit.sbnriassignment.Utils;
import com.sumit.sbnriassignment.model.Repo;
import com.sumit.sbnriassignment.model.RepoDb;
import com.sumit.sbnriassignment.model.ResponseContainer;
import com.sumit.sbnriassignment.repo.CommonRepository;
import com.sumit.sbnriassignment.repo.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private Application application;
    private AppDatabase appDatabase;
    private CommonRepository commonRepository;
    private List<RepoDb> repoDbListOffline = new ArrayList<>();


    public MainViewModel(Application application) {
        super(application);
        this.application = application;

        appDatabase = AppDatabase.getDatabase(application);
        commonRepository = new CommonRepository();

    }

    public MutableLiveData<ResponseContainer> getRepos(int page) {
        MutableLiveData<ResponseContainer> liveData = null;
        boolean isNetworkAvaialble = Utils.isNetworkAvailable(application);
        if (Utils.isDataStoredInDb(application) && !isNetworkAvaialble) {
            liveData = getRepoFromLocal();
        } else {
            liveData = getRepoFromService(page);
        }

        return liveData;
    }

    public List<RepoDb> convertServiceRepos(ArrayList<Repo> repos) {
        List<RepoDb> repoDbList = new ArrayList<>();
        for (int i = 0; i < repos.size(); i++) {
            RepoDb repoDb = new RepoDb(repos.get(i));
            repoDbList.add(repoDb);
        }

        return repoDbList;
    }


    public void insertRepoInDB(ArrayList<Repo> repoArrayList) {

        ArrayList<RepoDb> localDbArrayList = new ArrayList<>();
        for (int i = 0; i < repoArrayList.size(); i++) {
            Repo repo = repoArrayList.get(i);
            RepoDb repoDb = new RepoDb(repo);
            localDbArrayList.add(repoDb);
        }

        new AddAsyncTask(appDatabase).execute(localDbArrayList);

        Utils.updateDataStoredInDb(application, true);
    }


    private class AddAsyncTask extends AsyncTask<List<RepoDb>, Void, Void> {

        private AppDatabase db;

        AddAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final List<RepoDb>... params) {
            db.getRepoDao().insertAllRepos(params[0]);
            System.out.println("data inserted");
            return null;
        }

    }

    public MutableLiveData<ResponseContainer> getRepoFromService(int page) {
        MutableLiveData<ResponseContainer> data = new MutableLiveData<>();
        commonRepository.getRepos(page).subscribeWith(new SingleObserver<ArrayList<Repo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ArrayList<Repo> repos) {
                ResponseContainer responseContainer = new ResponseContainer(Utils.SERVICE_CALL, convertServiceRepos(repos));
                data.setValue(responseContainer);

                insertRepoInDB(repos);


            }

            @Override
            public void onError(Throwable e) {

            }
        });
        return data;
    }

    public MutableLiveData<ResponseContainer> getRepoFromLocal() {
        MutableLiveData<ResponseContainer> data = new MutableLiveData<>();

        appDatabase.getRepoDao().getReposList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new SingleObserver<List<RepoDb>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<RepoDb> repoDbs) {
                ResponseContainer responseContainer = new ResponseContainer(Utils.DB_CALL, repoDbs);
                data.setValue(responseContainer);
                System.out.println("data read:::" + repoDbs.size());
            }

            @Override
            public void onError(Throwable e) {
                data.setValue(null);

            }
        });

        return data;

    }

    public List<RepoDb> getRepoDbListOffline() {
        return repoDbListOffline;
    }

    public void setRepoDbListOffline(List<RepoDb> repoDbListOffline) {
        this.repoDbListOffline = repoDbListOffline;
    }
}
