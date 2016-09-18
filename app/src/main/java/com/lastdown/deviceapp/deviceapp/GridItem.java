package com.lastdown.deviceapp.deviceapp;

/**
 * Created by Martijn on 18/09/2016.
 */
public class GridItem {

    private String title;
    private int image;

    public GridItem(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
