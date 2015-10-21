package com.kirer.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kirer.R;
import com.kirer.common.BaseActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by tiptimes on 15/8/7.
 */
public class UserCenterActivity extends BaseActivity {

    private TextView showUser;
    private Button loginOut;

    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_center);
        showUser = (TextView) findViewById(R.id.show);
        loginOut = (Button) findViewById(R.id.login_out);

        BmobUser currentUser = BmobUser.getCurrentUser(UserCenterActivity.this);
        if (currentUser != null) {
            showUser.setText("UserName -> " + currentUser.getUsername() + "\nPassword -> " + currentUser.getPassword());
        }

        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut(UserCenterActivity.this);
                finish();
            }
        });
    }
}
