package com.sumit.sbnriassignment.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sumit.sbnriassignment.R;
import com.sumit.sbnriassignment.Utils;
import com.sumit.sbnriassignment.model.RepoDb;
import com.sumit.sbnriassignment.model.ResponseContainer;
import com.sumit.sbnriassignment.view.adapter.RepoAdapter;
import com.sumit.sbnriassignment.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MainViewModel mainViewModel;
    private RecyclerView recyclerViewRepos;
    private ArrayList<RepoDb> repoArrayList = new ArrayList<RepoDb>();
    private RepoAdapter repoAdapter;
    private int page = 1;
    private LinearLayout layoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initViews();
        setProperties();
        getRepos(page, true, false);


    }

    /**
     * This method find and init the requried views
     */
    public void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        layoutProgress = (LinearLayout) findViewById(R.id.layout_progress);
        recyclerViewRepos = findViewById(R.id.recycler_view_repos);
    }

    /**
     * this method set properties like click listener and added another properties
     */
    public void setProperties() {


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        repoAdapter = new RepoAdapter(this, repoArrayList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewRepos.setAdapter(repoAdapter);
        recyclerViewRepos.setLayoutManager(layoutManager);
        recyclerViewRepos.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //System.out.println("data:::" + layoutManager.getChildCount() + " :::" + layoutManager.getItemCount() + " :::" + layoutManager.findFirstVisibleItemPosition());

                if (layoutManager.getChildCount() + layoutManager.findFirstVisibleItemPosition() == page * 10 - 1) {
                    page++;
                    getRepos(page, false, true);
                }

            }
        });


    }


    public void getRepos(int page, boolean showMainLoader, boolean showBottomLoader) {

        // handling screen orientation and getting local data from view model.
        List<RepoDb> repoDbOfflineList = mainViewModel.getRepoDbListOffline();
        if (repoDbOfflineList != null && repoDbOfflineList.size() > 0 && repoArrayList.size() < repoDbOfflineList.size()) {
            repoArrayList.addAll(mainViewModel.getRepoDbListOffline());
            repoAdapter.notifyDataSetChanged();

            this.page = repoDbOfflineList.size() / 10;
            if (repoDbOfflineList.size() % 10 != 0) {
                this.page++;
            }
            return;
        }


        if (showMainLoader) {
            Utils.showProgressDialog(this);
        }
        if (showBottomLoader) {
            layoutProgress.setVisibility(View.VISIBLE);
        }

        mainViewModel.getRepos(page).observe(this, new Observer<ResponseContainer>() {
            @Override
            public void onChanged(ResponseContainer responseContainer) {

                if (showMainLoader) {
                    Utils.dismissDialog();
                }
                if (showBottomLoader) {
                    layoutProgress.setVisibility(View.GONE);
                }

                if (responseContainer != null) {
                    if (responseContainer.getType() == Utils.DB_CALL) {
                        repoArrayList.clear();
                        mainViewModel.getRepoDbListOffline().clear();
                    }
                    if (responseContainer.getRepoDbList() != null) {
                        repoArrayList.addAll(responseContainer.getRepoDbList());
                        repoAdapter.notifyDataSetChanged();

                        mainViewModel.setRepoDbListOffline(repoArrayList);
                    }

                }
            }
        });

    }


}
