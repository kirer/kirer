package com.kirer.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kirer.R;
import com.kirer.common.BaseActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by tiptimes on 15/8/7.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private EditText usernameEt, passwordEt;
    private Button loginBtn, registerBtn;
    private BmobUser bmobUser;

    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        bmobUser = new BmobUser();
        initView();
        if (BmobUser.getCurrentUser(this) != null) {
            Toast.makeText(LoginActivity.this, "Current User-->" + BmobUser.getCurrentUser(this).getUsername(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        usernameEt = (EditText) findViewById(R.id.username);
        passwordEt = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        registerBtn = (Button) findViewById(R.id.register);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                BmobUser.logOut(LoginActivity.this);
                bmobUser.login(LoginActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(LoginActivity.this, "Login Success-->" + bmobUser.getObjectId(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(LoginActivity.this, "Login Failed-->" + i + " msg " + s, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.register:
                bmobUser.setUsername(usernameEt.getText().toString());
                bmobUser.setPassword(passwordEt.getText().toString());
                bmobUser.signUp(LoginActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(LoginActivity.this, "Register Success-->", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(LoginActivity.this, "Register Failed-->" + s, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
