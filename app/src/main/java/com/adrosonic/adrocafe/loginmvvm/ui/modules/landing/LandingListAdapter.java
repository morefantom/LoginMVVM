package com.adrosonic.adrocafe.loginmvvm.ui.modules.landing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adrosonic.adrocafe.loginmvvm.R;
import com.adrosonic.adrocafe.loginmvvm.data.ApiResponse;
import com.adrosonic.adrocafe.loginmvvm.databinding.ItemLandingBinding;

import java.util.List;

public class LandingListAdapter extends RecyclerView.Adapter<LandingListAdapter.ViewHolder>{

    private List<ApiResponse> apiResponseList;
    private Context context;

    LandingListAdapter(List<ApiResponse> apiResponseList, Context context){
        this.apiResponseList = apiResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLandingBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_landing, parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApiResponse apiResponse = apiResponseList.get(position);
        holder.bind(apiResponse);
    }

    @Override
    public int getItemCount() {
        return apiResponseList.size();
    }

    public void swap(List<ApiResponse> apiResponseList){
        LandingDiffCallback diffCallback = new LandingDiffCallback(this.apiResponseList, apiResponseList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.apiResponseList = apiResponseList;
        diffResult.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ItemLandingBinding binding;

        public ViewHolder(ItemLandingBinding itemLandingBinding) {
            super(itemLandingBinding.getRoot());
            this.binding = itemLandingBinding;
        }

        public void bind(ApiResponse apiResponse) {
            binding.setApiresponse(apiResponse);
            binding.executePendingBindings();
        }
    }
}
