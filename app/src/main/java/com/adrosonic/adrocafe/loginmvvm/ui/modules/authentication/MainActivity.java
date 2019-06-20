package com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.adrosonic.adrocafe.loginmvvm.R;
import com.adrosonic.adrocafe.loginmvvm.repository.PrefManager;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication.login.LoginFragment;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.landing.LandingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrefManager prefManager = new PrefManager(this);

        if (prefManager.isUserLoggedIn()){
            startActivity(new Intent(this, LandingActivity.class));
            finish();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_main, new LoginFragment(), "login")
                .commitNow();
    }
}
