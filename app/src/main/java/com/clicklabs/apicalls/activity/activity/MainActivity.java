package com.clicklabs.apicalls.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clicklabs.apicalls.R;
import com.clicklabs.apicalls.activity.communicator.FragmentCommunicator;
import com.clicklabs.apicalls.activity.fragment.UserDetailsFragment;
import com.clicklabs.apicalls.activity.fragment.UserListFragment;
import com.clicklabs.apicalls.activity.model.User;
import com.clicklabs.apicalls.activity.util.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator {

    private UserListFragment mUserListFragment;
    private UserDetailsFragment mUserDetailsFragment;
    private ArrayList<User> listFromApi = new ArrayList<>();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            user=savedInstanceState.getParcelable(Constants.Keys.USERSAVED);
            if(user!=null) {
                passDetails(user);
            }
        }

    }

    /**
     * This passes user to the second fragment to show details.
     * @param mUser When the user is selected from the First Fragment containing RecyclerView User List
     */
    @Override
    public void passDetails(User mUser) {

        UserDetailsFragment userDetailsFragment = (UserDetailsFragment) getSupportFragmentManager().
                findFragmentById(R.id.view_for_detailsFragment);

        assert userDetailsFragment != null;
        userDetailsFragment.showDetails(mUser);
        user = mUser;


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.Keys.USERSAVED,user);
    }
}

