package com.sumit.sbnriassignment.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sumit.sbnriassignment.R;
import com.sumit.sbnriassignment.model.RepoDb;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {


    private ArrayList<RepoDb> authorArrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public RepoAdapter(Context context, ArrayList<RepoDb> authorArrayList) {
        this.context = context;
        this.authorArrayList = authorArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_repo, parent,false);
        RepoViewHolder authorViewHolder = new RepoViewHolder(view);
        return authorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        RepoDb repoDb = authorArrayList.get(position);
        holder.tvName.setText(repoDb.getName());
        holder.tvDesc.setText(repoDb.getDesc());

        holder.tvPermission.setText(repoDb.getPermission());
        holder.tvLicense.setText(repoDb.getLicense());

        holder.tvIssueCount.setText(repoDb.getIssueCount());

    }

    @Override
    public int getItemCount() {
        return authorArrayList.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDesc;
        private TextView tvPermission;
        private TextView tvLicense;
        private TextView tvIssueCount;
        private CardView cardParent;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            cardParent = itemView.findViewById(R.id.card_parent);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvPermission = itemView.findViewById(R.id.tv_permission);
            tvLicense = itemView.findViewById(R.id.tv_license);
            tvIssueCount = itemView.findViewById(R.id.tv_issues);
        }
    }

}
