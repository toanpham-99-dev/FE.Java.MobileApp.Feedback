package com.example.feedbackapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;

import java.util.ArrayList;
import java.util.List;

public class CustomApdapterModule extends BaseAdapter {
    private LayoutInflater flater;
    private ArrayList<com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module> list;
    private int listItemLayoutResource;
    private int textViewItemNameId;
    // Arguments example:
    //  @listItemLayoutResource: R.layout.spinner_item_layout_resource
    //        (File: layout/spinner_item_layout_resource.xmll)
    //  @textViewItemNameId: R.id.textView_item_name
    //        (A TextVew in file layout/spinner_item_layout_resource.xmlxml)
    //  @textViewItemPercentId: R.id.textView_item_percent
    //        (A TextVew in file layout/spinner_item_layout_resource.xmll)
    // structure adapter for class
    public CustomApdapterModule(Activity context, int listItemLayoutResource,
                                int textViewItemNameId,
                                ArrayList<com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module> list) {
        this.listItemLayoutResource = listItemLayoutResource;

        this.textViewItemNameId = textViewItemNameId;
        this.list = list;
        this.flater = context.getLayoutInflater();
    }

    public int getCount() {
        if(this.list == null)  {
            return 0;
        }
        return this.list.size();
    }


    public Object getItem(int position) {
        return this.list.get(position);
    }


    public long getItemId(int position) {
        Module cla =(Module) this.getItem(position);
       // return cla.getModuleId();
         return position; //(Return position if you need).
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        Module clas = (Module) getItem(position);

        // Example: @listItemLayoutResource: R.layout.spinner_item_layout_resource
        // (File: layout/spinner_item_layout_resourcerce.xml)
        View rowView = this.flater.inflate(this.listItemLayoutResource, null,true);

        // Example: @textViewItemNameId: R.id.textView_item_name
        // (A TextView in file layout/spinner_item_layout_resourcerce.xml)
        TextView textViewItemName = (TextView) rowView.findViewById(this.textViewItemNameId);
        textViewItemName.setText(clas.getModuleName());

        // Example: @textViewItemPercentId: R.id.textView_item_percent
        // (A TextView in file layout/spinner_item_layout_resource.xmlxml)
        return rowView;
    }
}
