package com.benmacleod.homework_02_colorswitcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // List of colors we have saved.
    ArrayList<ColorInfo> listOfColors = new ArrayList<ColorInfo>();

    // The active color values on the sliders.
    // TODO: Update to a ColorInfo class.
    ColorInfo activeColor;
    ColorInfoAdaptor listViewAdaptor;

    SeekBar j_seek_red_color;
    SeekBar j_seek_green_color;
    SeekBar j_seek_blue_color;
    TextView j_txt_red_color_value;
    TextView j_txt_green_color_value;
    TextView j_txt_blue_color_value;
    TextView j_txt_hex_value;
    TextView j_txt_rgb_value;
    TextView j_txt_seek_red_label;
    TextView j_txt_seek_green_label;
    TextView j_txt_seek_blue_label;
    TextView j_txt_hex_label;
    TextView j_txt_rgb_label;

    Button j_btn_save_color;
    ListView j_lv_list_of_colors;
    ConstraintLayout j_main_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activeColor = new ColorInfo(0,0,0);

        j_seek_red_color = findViewById(R.id.ui_seek_red_color);
        j_seek_green_color = findViewById(R.id.ui_seek_green_color);
        j_seek_blue_color = findViewById(R.id.ui_seek_blue_color);
        j_txt_red_color_value = findViewById(R.id.txt_red_color_value);
        j_txt_green_color_value = findViewById(R.id.txt_green_color_value);
        j_txt_blue_color_value = findViewById(R.id.txt_blue_color_value);
        j_txt_hex_value = findViewById(R.id.txt_ui_hex_value);
        j_txt_rgb_value = findViewById(R.id.txt_ui_rgb_value);
        j_main_layout = findViewById(R.id.main_layout);
        j_btn_save_color = findViewById(R.id.btn_ui_save_color);
        j_lv_list_of_colors = findViewById(R.id.lv_ui_list_of_colors);
        j_txt_seek_red_label = findViewById(R.id.txt_ui_seek_label_red);
        j_txt_seek_green_label = findViewById(R.id.txt_ui_seek_label_green);
        j_txt_seek_blue_label = findViewById(R.id.txt_ui_seek_label_blue);
        j_txt_rgb_label = findViewById(R.id.txt_ui_rgb_label);
        j_txt_hex_label = findViewById(R.id.txt_ui_hex_label);

        initSystem();

        mountSliderListeners(j_seek_red_color, j_txt_red_color_value, "red");
        mountSliderListeners(j_seek_green_color, j_txt_green_color_value, "green");
        mountSliderListeners(j_seek_blue_color, j_txt_blue_color_value, "blue");
        mountSaveButtonClickListener();
        mountListViewClickListener();
    }

    public void initSystem()
    {
        // Setup list view adaptor.
        listViewAdaptor = new ColorInfoAdaptor(this, listOfColors);
        j_lv_list_of_colors.setAdapter(listViewAdaptor);

        setBackgroundWithRGB(activeColor.getRedValue(), activeColor.getGreenValue(), activeColor.getBlueValue());
        setColorValueTextViews();

        // Set seek bar progress values.
        j_seek_red_color.setProgress(activeColor.getRedValue());
        j_seek_green_color.setProgress(activeColor.getGreenValue());
        j_seek_blue_color.setProgress(activeColor.getBlueValue());

        // Set the Seek text values.
        j_txt_red_color_value.setText(activeColor.getRedValue() + "");
        j_txt_green_color_value.setText(activeColor.getGreenValue() + "");
        j_txt_blue_color_value.setText(activeColor.getBlueValue() + "");

    }

    public void setSeekBarValues(int r, int g, int b)
    {
        // Set progress values
        j_seek_red_color.setProgress(r);
        j_seek_green_color.setProgress(g);
        j_seek_blue_color.setProgress(b);

        // Set the Seek text values.
        j_txt_red_color_value.setText(r + "");
        j_txt_green_color_value.setText(g + "");
        j_txt_blue_color_value.setText(b + "");

    }

    public  void setBackgroundWithRGB(int red, int green, int blue)
    {
        int backgroundColor = Color.rgb(red, green, blue);
        j_main_layout.setBackgroundColor(backgroundColor);

        setTextColor();
    }

    public void setColorValueTextViews()
    {
        j_txt_rgb_value.setText(activeColor.getRGBString());
        j_txt_hex_value.setText(activeColor.getHEXString());
    }

    public void setTextColor()
    {
        if (activeColor.isWhite())
        {
            // Set Hex & RGB Label to black
            j_txt_hex_value.setTextColor(Color.BLACK);
            j_txt_hex_label.setTextColor(Color.BLACK);
            j_txt_rgb_value.setTextColor(Color.BLACK);
            j_txt_rgb_label.setTextColor(Color.BLACK);

            // Seekbar Text Values
            j_txt_red_color_value.setTextColor(Color.BLACK);
            j_txt_green_color_value.setTextColor(Color.BLACK);
            j_txt_blue_color_value.setTextColor(Color.BLACK);

            // Seekbar Labels
            j_txt_seek_red_label.setTextColor(Color.BLACK);
            j_txt_seek_green_label.setTextColor(Color.BLACK);
            j_txt_seek_blue_label.setTextColor(Color.BLACK);
        }
        else
        {
            // Set Hex & RGB Label to black
            j_txt_hex_value.setTextColor(Color.WHITE);
            j_txt_hex_label.setTextColor(Color.WHITE);
            j_txt_rgb_value.setTextColor(Color.WHITE);
            j_txt_rgb_label.setTextColor(Color.WHITE);

            // Seekbar Text Values
            j_txt_red_color_value.setTextColor(Color.WHITE);
            j_txt_green_color_value.setTextColor(Color.WHITE);
            j_txt_blue_color_value.setTextColor(Color.WHITE);

            // Seekbar Labels
            j_txt_seek_red_label.setTextColor(Color.WHITE);
            j_txt_seek_green_label.setTextColor(Color.WHITE);
            j_txt_seek_blue_label.setTextColor(Color.WHITE);
        }
    }

    public void mountSaveButtonClickListener()
    {
        j_btn_save_color.setOnClickListener(view -> {
            listOfColors.add(new ColorInfo(activeColor.getRedValue(), activeColor.getGreenValue(), activeColor.getBlueValue()));
            listViewAdaptor.notifyDataSetChanged();

            // Reset current color when it's saved.
            activeColor = new ColorInfo(0, 0, 0);
            setSeekBarValues(0, 0,0);
            setBackgroundWithRGB(0, 0,0);
            setColorValueTextViews();
        });
    }

    public void mountListViewClickListener()
    {
        j_lv_list_of_colors.setOnItemClickListener((adapterView, view, i, l) -> {
            ColorInfo color = listOfColors.get(i);
            activeColor = new ColorInfo(color.getRedValue(), color.getGreenValue(), color.getBlueValue());
            setBackgroundWithRGB(activeColor.getRedValue(), activeColor.getGreenValue(), activeColor.getBlueValue());
            setColorValueTextViews();
            setSeekBarValues(activeColor.getRedValue(), activeColor.getGreenValue(), activeColor.getBlueValue());
        });
    }

    public void mountSliderListeners(SeekBar seekBar, TextView valueText, String key)
    {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueText.setText(i + "");

                // If this key is red, set the red value.
                if (key.equals("red"))
                {
                    activeColor.setRedValue(i);
                }

                if (key.equals("green"))
                {
                    activeColor.setGreenValue(i);
                }

                if (key.equals("blue"))
                {
                    activeColor.setBlueValue(i);
                }

                setBackgroundWithRGB(activeColor.getRedValue(), activeColor.getGreenValue(), activeColor.getBlueValue());
                setColorValueTextViews();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}