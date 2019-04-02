package com.clicklabs.apicalls.activity.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.clicklabs.apicalls.R;
import com.clicklabs.apicalls.activity.listener.OnUserItemClickListener;
import com.clicklabs.apicalls.activity.model.User;

import java.util.ArrayList;

public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserListRecyclerViewAdapter.UserViewHolder> {

    private ArrayList<User> adapterList = new ArrayList<>();
    private OnUserItemClickListener mListener;

    public UserListRecyclerViewAdapter(ArrayList<User> adapterList, OnUserItemClickListener listener) {
        this.adapterList = adapterList;
        mListener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.user_view_container,viewGroup,false);


        return new UserViewHolder(view,mListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, int position) {

        userViewHolder.tvName.setText(adapterList.get(position).getName());
        userViewHolder.tvId.setText(adapterList.get(position).getId().toString());
        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(userViewHolder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvId;
        ImageView ivDisplayPic;

        public UserViewHolder(@NonNull View itemView, final OnUserItemClickListener listener) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvId = itemView.findViewById(R.id.tvId);


        }
    }

}
