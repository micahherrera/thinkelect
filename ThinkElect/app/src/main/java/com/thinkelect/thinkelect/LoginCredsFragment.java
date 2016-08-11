package com.thinkelect.thinkelect;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by micahherrera on 8/10/16.
 */
public class LoginCredsFragment extends Fragment implements View.OnClickListener{
    EditText emailText;
    EditText passwordText;
    Button login;
    Button forgotPassword;
    LoginWithCreds mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_creds_fragment, container, false);
        emailText = (EditText)view.findViewById(R.id.email);
        passwordText = (EditText)view.findViewById(R.id.password);
        login = (Button)view.findViewById(R.id.button_login);
        forgotPassword = (Button)view.findViewById(R.id.button_forgot);
        login.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_login && !emailText.getText().toString().equals("")
                && !passwordText.getText().toString().equals("")){
            mListener.loginWithCreds(emailText.getText().toString(),
                    passwordText.getText().toString());
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (LoginWithCreds) getActivity();
    }

    public interface LoginWithCreds{
        void loginWithCreds(String username, String password);
    }
}
