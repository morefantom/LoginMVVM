package com.adrosonic.adrocafe.loginmvvm.ui.modules.landing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.adrosonic.adrocafe.loginmvvm.R;
import com.adrosonic.adrocafe.loginmvvm.repository.PrefManager;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication.MainActivity;

public class LandingActivity extends AppCompatActivity {

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);
        prefManager = new PrefManager(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new LandingFragment())
                    .commitNow();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.landingmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout){
            prefManager.saveLogin(false);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        return true;
    }
}
