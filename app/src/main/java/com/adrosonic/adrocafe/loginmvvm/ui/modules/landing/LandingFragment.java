package com.adrosonic.adrocafe.loginmvvm.ui.modules.landing;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.adrosonic.adrocafe.loginmvvm.R;
import com.adrosonic.adrocafe.loginmvvm.data.ApiResponse;
import com.adrosonic.adrocafe.loginmvvm.databinding.LandingFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LandingFragment extends Fragment {

    private LandingViewModel mViewModel;
    private LandingFragmentBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.landing_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LandingViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.getResponses().observe(this, new Observer<List<ApiResponse>>() {
            @Override
            public void onChanged(List<ApiResponse> apiResponseList) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Map<String, List<ApiResponse>> apiResponseMap = apiResponseList.stream().collect(Collectors.groupingBy(ApiResponse::getUserId));
                    List<String> titleList = new ArrayList<String>(apiResponseMap.keySet());
                    ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getActivity(), titleList, apiResponseMap);
                    binding.expandableListView.setAdapter(expandableListAdapter);
                }
            }
        });
    }
}
