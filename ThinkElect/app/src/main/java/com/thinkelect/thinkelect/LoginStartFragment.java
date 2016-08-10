package com.thinkelect.thinkelect;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class LoginStartFragment extends Fragment implements View.OnClickListener {
    Button loginButton;
    Button guestButton;
    StartScreen mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_start_fragment, container, false);
        loginButton = (Button)view.findViewById(R.id.button_login);
        guestButton = (Button)view.findViewById(R.id.button_guest);

        loginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (StartScreen) getActivity();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_login){
            mListener.loginClicked();
        }
        if(view.getId()==R.id.button_guest){
            mListener.guestClicked();
        }
    }

    public interface StartScreen{
        void loginClicked();
        void guestClicked();
    }
}