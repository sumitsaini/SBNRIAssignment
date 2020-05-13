package com.sumit.sbnriassignment.repo.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.sumit.sbnriassignment.model.RepoDb;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepos(RepoDb repoDb);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllRepos(List<RepoDb> repoDbList);

    @Query("select * from repos")
    Single<List<RepoDb>> getReposList();

    @Query("select Count(id) from repos")
    Single<Integer> getTotalItemCount();


}
