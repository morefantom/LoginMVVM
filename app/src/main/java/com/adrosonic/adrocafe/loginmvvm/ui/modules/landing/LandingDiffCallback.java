package com.adrosonic.adrocafe.loginmvvm.ui.modules.landing;

import androidx.recyclerview.widget.DiffUtil;

import com.adrosonic.adrocafe.loginmvvm.data.ApiResponse;

import java.util.List;
import java.util.Objects;

public class LandingDiffCallback extends DiffUtil.Callback {

    private List<ApiResponse> oldList;
    private List<ApiResponse> newList;

    LandingDiffCallback(List<ApiResponse> oldList, List<ApiResponse> newList){
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldList.get(oldItemPosition).getId(), newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldList.get(oldItemPosition).getTitle(), newList.get(newItemPosition).getTitle());
    }
}
