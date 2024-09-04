package com.example.feedbackapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.feedbackapp.R;

import java.util.ArrayList;

public class CustomItemAdapter extends ArrayAdapter<CustomItem> {

    public CustomItemAdapter(@NonNull Context context, ArrayList<CustomItem> customList){
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spiner_layout, parent, false);
        }

        CustomItem item = getItem(position);
        TextView spinerTv = convertView.findViewById(R.id.tvSpinnerLayout);

        if(item != null){
            spinerTv.setText(item.getSpinerItemName());
        }

        return convertView;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown_layout, parent, false);
        }

        CustomItem item = getItem(position);
        TextView dropdownTv = convertView.findViewById(R.id.tvDropdownLayout);

        if(item != null){
            dropdownTv.setText(item.getSpinerItemName());
        }

        return convertView;
    }
}
