package com.benmacleod.homework_02_colorswitcher;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorInfoAdaptor extends BaseAdapter
{
    Context ctx;
    ArrayList<ColorInfo> listOfColors;

    public ColorInfoAdaptor(Context ctx, ArrayList<ColorInfo> lOfC )
    {
        this.ctx = ctx;
        listOfColors = lOfC;
    }

    @Override
    public int getCount() {
        return listOfColors.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfColors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.color_view, null);
        }

        TextView hexValue = view.findViewById(R.id.txt_ui_color_hex_value);
        TextView rgbValue = view.findViewById(R.id.txt_ui_color_rgb_value);
        TextView hexLabel = view.findViewById(R.id.txt_ui_color_hex_label);
        TextView rgbLabel = view.findViewById(R.id.txt_ui_color_rgb_label);

        ColorInfo color = listOfColors.get(i);
        hexValue.setText(color.getHEXString());
        rgbValue.setText(color.getRGBString());

        if (color.isWhite())
        {
            hexValue.setTextColor(Color.BLACK);
            rgbValue.setTextColor(Color.BLACK);
            hexLabel.setTextColor(Color.BLACK);
            rgbLabel.setTextColor(Color.BLACK);


        }

        view.setBackgroundColor(Color.rgb(color.getRedValue(), color.getGreenValue(), color.getBlueValue()));

        return view;
    }
}
