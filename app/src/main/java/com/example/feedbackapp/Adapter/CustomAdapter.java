package com.example.feedbackapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.model.Class;

import java.util.List;

public class CustomAdapter extends BaseAdapter  {

    private LayoutInflater flater;
    private List<Classs> list;
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
    public CustomAdapter(Activity context, int listItemLayoutResource,
                         int textViewItemNameId,
                         List<Classs> list) {
        this.listItemLayoutResource = listItemLayoutResource;

        this.textViewItemNameId = textViewItemNameId;
        this.list = list;
        this.flater = context.getLayoutInflater();
    }
    @Override
    public int getCount() {
        if(this.list == null)  {
            return 0;
        }
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {

        Classs cla =(Classs)this.getItem(position);
        return position;
        // return position; (Return position if you need).
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Classs clas = (Classs) getItem(position);

        // Example: @listItemLayoutResource: R.layout.spinner_item_layout_resource
        // (File: layout/spinner_item_layout_resourcerce.xml)
        View rowView = this.flater.inflate(this.listItemLayoutResource, null,true);

        // Example: @textViewItemNameId: R.id.textView_item_name
        // (A TextView in file layout/spinner_item_layout_resourcerce.xml)
        TextView textViewItemName = (TextView) rowView.findViewById(this.textViewItemNameId);
        textViewItemName.setText(clas.getClassName());

        // Example: @textViewItemPercentId: R.id.textView_item_percent
        // (A TextView in file layout/spinner_item_layout_resource.xmlxml)
        return rowView;
    }
}