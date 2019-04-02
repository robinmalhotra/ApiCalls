package com.clicklabs.apicalls.activity.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clicklabs.apicalls.R;
import com.clicklabs.apicalls.activity.model.User;
import com.clicklabs.apicalls.activity.model.UserPosts;

import java.util.ArrayList;

public class UserPostsRecyclerAdapter extends RecyclerView.Adapter<UserPostsRecyclerAdapter.PostsViewHolder> {

    private ArrayList<UserPosts> userPostsAdapterList;

    public UserPostsRecyclerAdapter(ArrayList<UserPosts> userPostsAdapterList) {
        this.userPostsAdapterList = userPostsAdapterList;
        Log.d("yyyyyy", "UserPostsRecyclerAdapter: " +userPostsAdapterList.size());
    }

    @NonNull
    @Override
    public UserPostsRecyclerAdapter.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.user_posts_container,viewGroup,false);

        PostsViewHolder holder = new PostsViewHolder(view);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder postsViewHolder, int position) {

        postsViewHolder.tvUserId.setText(userPostsAdapterList.get(position).getId().toString());
        postsViewHolder.tvUserBody.setText(userPostsAdapterList.get(position).getBody());
        postsViewHolder.tvTitle.setText(userPostsAdapterList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return userPostsAdapterList.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{

        TextView tvUserId, tvUserBody, tvTitle;


        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserId = itemView.findViewById(R.id.Id);
            tvTitle = itemView.findViewById(R.id.userTitle);
            tvUserBody = itemView.findViewById(R.id.userBody);
        }
    }


}
