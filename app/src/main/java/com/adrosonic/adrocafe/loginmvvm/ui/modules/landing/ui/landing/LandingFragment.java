package com.adrosonic.adrocafe.loginmvvm.ui.modules.landing.ui.landing;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adrosonic.adrocafe.loginmvvm.R;

public class LandingFragment extends Fragment {

    private LandingViewModel mViewModel;

    public static LandingFragment newInstance() {
        return new LandingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.landing_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LandingViewModel.class);
        // TODO: Use the ViewModel
    }

}
