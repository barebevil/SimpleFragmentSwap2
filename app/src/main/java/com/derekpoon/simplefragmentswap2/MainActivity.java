package com.derekpoon.simplefragmentswap2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/*
Fragment swapping 1 activity, with a listener interface defined in the Main Fragment
Buttons are defined in each of the fragments
 */

//Fragment transactions should be handled in the activity
// Activity implements the fragment listener to handle events

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentSwapListener {

    MainFragment mainFragment;
    private final String FRAGMENT_TAG = "frag";

    // Now we can define the action to take in the activity when the fragment event fires
    // This is implementing the `FragmentSwapListener` interface methods
    @Override
    public void replaceFragment(int id){
        if (id == 0) {
            MainFragment fragment0 = new MainFragment();
            swapFragment(fragment0);
        }
        if (id == 1) {
            FirstFragment fragment1 = new FirstFragment();
            swapFragment(fragment1);
        }
        if (id == 2) {
            SecondFragment fragment2 = new SecondFragment();
            swapFragment(fragment2);
        }
    }

    public void swapFragment(Fragment swapfragment) {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container, swapfragment);

        // Complete the changes added above
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if the fragment exists
        if (savedInstanceState != null) {
            // look up the instance that already exists by tag
//            firstFragment = (FirstFragment)
//                    getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);

            //if fragment doesn't exist, example, launching the app for the first time
        } else if (mainFragment == null) {
            // create a new fragment
            mainFragment = new MainFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, mainFragment, FRAGMENT_TAG);
            ft.commit();
        }
    }
}
