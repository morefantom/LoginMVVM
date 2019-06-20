package com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication.signup;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adrosonic.adrocafe.loginmvvm.R;
import com.adrosonic.adrocafe.loginmvvm.databinding.FragmentSignupBinding;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication.AuthViewModel;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.landing.LandingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private AuthViewModel mViewModel;

    private FragmentSignupBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        binding.setViewModel(mViewModel);

        mViewModel.getEditTextPasswordConfirm().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals(mViewModel.getEditTextPassword().getValue())){
                    mViewModel.isValid.set(true);
                }else mViewModel.isValid.set(false);
            }
        });

        mViewModel.navigateTo.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("Landing")){
                    if (getActivity() != null){
                        getActivity().startActivity(new Intent(getActivity(), LandingActivity.class));
                        getActivity().finish();
                    }
                }
            }
        });
    }
}
