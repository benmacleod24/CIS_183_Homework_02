package com.benmacleod.homework_02_colorswitcher;

public class ColorInfo {

    private int redValue;

    private int greenValue;
    private int blueValue;

    public ColorInfo()
    {}

    public ColorInfo(int _r, int _g, int _b)
    {
        redValue = _r;
        greenValue = _g;
        blueValue = _b;
    }

    public String getRGBString()
    {
        return "RGB(" + redValue + "," + greenValue + "," + blueValue + ")";
    }

    public String getHEXString()
    {
        return String.format("#%02X%02X%02X", redValue, greenValue, blueValue);
    }

    public void setRedValue(int redValue)
    {
        this.redValue = redValue;
    }

    public void setGreenValue(int greenValue)
    {
        this.greenValue = greenValue;
    }

    public void setBlueValue(int blueValue)
    {
        this.blueValue = blueValue;
    }

    public int getRedValue()
    {
        return redValue;
    }

    public int getGreenValue()
    {
        return greenValue;
    }

    public int getBlueValue()
    {
        return blueValue;
    }

    public boolean isWhite()
    {
        if (redValue >= 200 && greenValue >= 200 && blueValue >= 200)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
}
