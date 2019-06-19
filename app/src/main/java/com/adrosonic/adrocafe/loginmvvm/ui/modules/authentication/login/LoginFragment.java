package com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication.login;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adrosonic.adrocafe.loginmvvm.R;
import com.adrosonic.adrocafe.loginmvvm.databinding.FragmentLoginBinding;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication.AuthViewModel;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication.signup.SignupFragment;
import com.adrosonic.adrocafe.loginmvvm.ui.modules.landing.LandingActivity;

public class LoginFragment extends Fragment {

    private AuthViewModel mViewModel;

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        // TODO: Use the ViewModel
        binding.setViewModel(mViewModel);

        mViewModel.navigateTo.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (getFragmentManager() != null)
                    if (s.equals("Landing")){
                        if (getActivity() != null){
                            getActivity().startActivity(new Intent(getActivity(), LandingActivity.class));
                            getActivity().finish();
                        }
                    }else {
                        getFragmentManager().beginTransaction().replace(R.id.container_main, new SignupFragment(), "Sign Up").addToBackStack(null).commit();
                    }
            }
        });
    }

}
