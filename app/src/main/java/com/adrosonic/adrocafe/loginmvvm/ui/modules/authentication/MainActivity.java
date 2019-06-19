package com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.adrosonic.adrocafe.loginmvvm.R;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_main, new LoginFragment(), "login")
                .commitNow();
    }
}
