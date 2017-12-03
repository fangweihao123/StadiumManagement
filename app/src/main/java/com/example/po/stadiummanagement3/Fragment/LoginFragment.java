package com.example.po.stadiummanagement3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.po.stadiummanagement3.Activity.MainActivity;
import com.example.po.stadiummanagement3.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 13701 on 2017/12/3.
 */

public class LoginFragment extends Fragment {
    @BindView(R.id.text_input_password)
    public EditText password;
    @BindView(R.id.input_account)
    public EditText account;
    @BindView(R.id.btn_login)
    public AppCompatButton loginButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.login_fragment,container,false);
        ButterKnife.bind(this,v);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return v;
    }
}
