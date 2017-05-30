package com.ffb.friendsfootbets.databaselink;

import android.util.Log;

import com.ffb.friendsfootbets.models.Tournament;
import com.ffb.friendsfootbets.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by Fouad-Sams on 29/05/2017.
 */

public class LoadUsersList {

    // Attributes related to database link
    private DatabaseReference mDatabase;
    private LoadUsersListListener listener;

    // Attributes related to the objects that are handled, key : id, value : tournament instance
    public HashMap<String, User> usersListMap;


    public LoadUsersList() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.listener = null;
    }

    // Assign the listener implementing events interface that will receive the events
    public void setLoadUsersListListener(LoadUsersListListener listener) {
        this.listener = listener;
    }

    /*
     * We implement a listener in order to get back to the initial activity after the data from the
     * database is loaded.
     */
    public interface LoadUsersListListener {
        // This trigger will be used every time we finish loading the tournaments related to a user
        public void onUsersListLoaded(HashMap<String, User> userListMap);
    }

    // this attribute enables to count how many tournaments have been downloaded from the database
    int usersLoadedCounter;
    int usersNumber;
    public void loadUsers(ArrayList<String> usernamesList){
        // We  initiate the counter
        usersLoadedCounter = 0;
        usersListMap = new HashMap<>();

        // We compute the number of users to load in order to know when to stop
        usersNumber = usernamesList.size();

        String tournamentId;
        for (int i = 0; i < usernamesList.size(); i++){
            String username = usernamesList.get(i);
            User user = new User(username);
            usersListMap.put(username, user);

            loadUser(username);
        }

    }
    /*
     * This method loads from the database a single tournament.
     */
    public void loadUser(final String username){
        // We set the references for the data we want from the database
        DatabaseReference usersRef = mDatabase.child("users").child(username);

        // We create the one time listener that will connect to the database
        ValueEventListener usersListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get User object
                User user = usersListMap.get(username);
                user.setName((String) dataSnapshot.child("name").getValue());
                user.setEmail((String) dataSnapshot.child("email").getValue());
                // In some cases the profilePicture key isn't set (when no picture is chosen)
                Object tempBoolProfilePicture = dataSnapshot.child("profilePicture").getValue();
                user.setProfilePicture((tempBoolProfilePicture != null) && (boolean) tempBoolProfilePicture);
                userLoadedTrigger();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        // We associate the two
        usersRef.addListenerForSingleValueEvent(usersListener);
    }

    private void userLoadedTrigger(){
        // Each time this method is called we increment the counter
        usersLoadedCounter++;

        // The third time this method is called we can trigger onUserLoaded to enable the activity
        // to resume
        if (usersLoadedCounter == usersNumber && listener != null){
            listener.onUsersListLoaded(usersListMap);
        }
    }

    public void searchUsername(String usernameQuery){
        Query query = mDatabase.child("users").orderByKey().startAt(null, usernameQuery).endAt(null, usernameQuery+'~');
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = "";
                ArrayList<String> usernameList = new ArrayList<String>();
                for (DataSnapshot usersSnapshot : dataSnapshot.getChildren()) {
                    username = usersSnapshot.getKey();
                    usernameList.add(username);
                }

                if (usernameList.size() > 0){
                    loadUsers(usernameList);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}