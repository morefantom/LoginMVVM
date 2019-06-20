package com.adrosonic.adrocafe.loginmvvm.ui.modules.landing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.databinding.DataBindingUtil;

import com.adrosonic.adrocafe.loginmvvm.R;
import com.adrosonic.adrocafe.loginmvvm.data.ApiResponse;
import com.adrosonic.adrocafe.loginmvvm.databinding.ItemLandingBinding;
import com.adrosonic.adrocafe.loginmvvm.databinding.ItemLandingChildBinding;

import java.util.List;
import java.util.Map;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> apiResponseList;
    private Map<String, List<ApiResponse>> apiResponseMap;

    public ExpandableListAdapter(Context context, List<String> apiResponseList, Map<String, List<ApiResponse>> apiResponseMap){
        this.context = context;
        this.apiResponseList = apiResponseList;
        this.apiResponseMap = apiResponseMap;
    }

    @Override
    public int getGroupCount() {
        return this.apiResponseList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.apiResponseMap.get(this.apiResponseList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.apiResponseList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.apiResponseMap.get(this.apiResponseList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ItemLandingBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_landing, parent, false);
            convertView = binding.getRoot();
        } else {
            binding = (ItemLandingBinding) convertView.getTag();
        }
        binding.setTitle(apiResponseList.get(groupPosition));
        convertView.setTag(binding);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemLandingChildBinding childBinding;
        if (convertView == null){
            childBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_landing_child, parent, false);
            convertView = childBinding.getRoot();
        } else {
            childBinding = (ItemLandingChildBinding) convertView.getTag();
        }
        childBinding.setApiresponse(apiResponseMap.get(this.apiResponseList.get(groupPosition)).get(childPosition));
        convertView.setTag(childBinding);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
