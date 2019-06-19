package com.adrosonic.adrocafe.loginmvvm.ui.modules.landing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.adrosonic.adrocafe.loginmvvm.R;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.landing.ui.landing.LandingFragment;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LandingFragment.newInstance())
                    .commitNow();
        }
    }
}
