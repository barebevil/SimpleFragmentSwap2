package com.derekpoon.simplefragmentswap2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SecondFragment extends Fragment {

    //define the listener for the interface type
    MainFragment.FragmentSwapListener fragswaplistener;

    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            fragswaplistener = (MainFragment.FragmentSwapListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentChangeListener");
        }
    }

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        Button mButtonMain = view.findViewById(R.id.btn_main_frag);
        mButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragswaplistener.replaceFragment(0);
            }
        });

        return view;
    }
}
