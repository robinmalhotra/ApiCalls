package com.clicklabs.apicalls.activity.communicator;

import android.os.Bundle;

import com.clicklabs.apicalls.activity.model.User;

/**
 * Interface to pass the User selected in the First Fragment containing RecyclerView User List.
 */

public interface FragmentCommunicator {
    void passDetails(User user);
}
