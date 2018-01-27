package com.derekpoon.simplefragmentswap2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainFragment extends Fragment {

    private final String FRAG1_TAG = "First Fragment";
    private EditText mEditText;
    private String mMessage;
    private Button mButtonFrag1, mButtonFrag2;

    //define the listener for the interface type
    private FragmentSwapListener fragswaplistener;

    //define the events that the fragment will use to communicate
    public interface FragmentSwapListener {
//        public void replaceFragment(Fragment fragment);
        public void replaceFragment(int id);
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", mMessage);
    }

    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            fragswaplistener = (FragmentSwapListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentChangeListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main Fragment", "onCreate() called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflates the fragment_main.xml
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }

    //is called after onCreateView()
    //any view setup should occur here, e.g. attaching listeners
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(FRAG1_TAG, "onViewCreated() called");

        mButtonFrag1 = view.findViewById(R.id.button_frag1);
        mButtonFrag1.setOnClickListener(btnHandler);

        mButtonFrag2 = view.findViewById(R.id.button_frag2);
        mButtonFrag2.setOnClickListener(btnHandler);

        mEditText = view.findViewById(R.id.mainfrag_edit_text);
        if (savedInstanceState != null) {
            Toast.makeText(getActivity(), "saveState = FOUND", Toast.LENGTH_SHORT).show();
            mMessage = savedInstanceState.getString("message");
            mEditText.setText(mMessage);
        }
    }

    //when the fragment is ready to be displayed on screen
    @Override
    public void onStart() {
        super.onStart();
        Log.d(FRAG1_TAG, "onStart() called");
    }

    //the fragment is not visible
    @Override
    public void onStop() {
        super.onStop();
        Log.d(FRAG1_TAG, "onStop() called");
    }

    //the fragment is running in the activity
    @Override
    public void onResume() {
        super.onResume();
        Log.d(FRAG1_TAG, "onResume() called");
    }

    //when another activity in the foreground has focus, but the activity hosting this fragment is still visible
    @Override
    public void onPause() {
        super.onPause();
        Log.d(FRAG1_TAG, "onPause() called");
    }

    // Now we can fire the event when the user selects a button in the fragment
    View.OnClickListener btnHandler = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_frag1:
                    fragswaplistener.replaceFragment(1);
                    Toast.makeText(getActivity(), "Displaying Fragment 1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_frag2:
                    fragswaplistener.replaceFragment(2);
                    Toast.makeText(getActivity(), "Displaying Fragment 2", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
