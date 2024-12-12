package com.sinhvien.venglorant;

public class VolcabularyItem {
    String Volcabulary;
    String Spelling;
    String Mean;
    String VolType;
    int imageView;

    public VolcabularyItem(String volcabulary, String spelling, String mean, String volType, int imageView) {
        Volcabulary = volcabulary;
        Spelling = spelling;
        Mean = mean;
        VolType = volType;
        this.imageView = imageView;
    }

    public String getVolcabulary() {
        return Volcabulary;
    }

    public String getSpelling() {
        return Spelling;
    }

    public String getMean() {
        return Mean;
    }

    public String getVolType() {
        return VolType;
    }

    public int getImageView() {
        return imageView;
    }
}
