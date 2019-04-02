package com.clicklabs.apicalls.activity.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.clicklabs.apicalls.R;
import com.clicklabs.apicalls.activity.adapter.UserListRecyclerViewAdapter;
import com.clicklabs.apicalls.activity.communicator.FragmentCommunicator;
import com.clicklabs.apicalls.activity.listener.OnUserItemClickListener;
import com.clicklabs.apicalls.activity.model.User;
import com.clicklabs.apicalls.activity.util.Constants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends Fragment implements OnUserItemClickListener  {

    private ArrayList<User> userArrayList = new ArrayList<>();
    private RecyclerView rvUserList;
    private UserListRecyclerViewAdapter adapter;
    private Context mContext;
    private FragmentCommunicator mCommunicator;
    private OnUserItemClickListener mListener;
    private ProgressBar progressBar;

    public UserListFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        mListener = this;
        init(view);

        return view;
    }

    /**
     * Initialized the components
     * @param view View type inflated for the corresponding XML.
     */
    private void init(View view) {

        populateUserList();
        adapter = new UserListRecyclerViewAdapter(userArrayList,mListener);
        rvUserList = view.findViewById(R.id.rvUserList);
        rvUserList.setLayoutManager(new LinearLayoutManager(mContext));
        rvUserList.setAdapter(adapter);

    }

    /**
     * Based on the UserArrayList that got passed from Spash Screen's API implementation, this function
     * populates the User List in this fragment.
     */
    public void populateUserList() {
        if(getActivity().getIntent().hasExtra(Constants.Keys.USERLISTFROMAPI)) {
            userArrayList = getActivity().getIntent().getParcelableArrayListExtra(Constants.Keys.USERLISTFROMAPI);

        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        try {
            mCommunicator=(FragmentCommunicator) mContext;
        }catch (ClassCastException e) {
            throw new ClassCastException(getString(R.string.error));
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int position) {
        mCommunicator.passDetails(userArrayList.get(position));
    }


}
