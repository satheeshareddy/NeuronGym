package com.edlogiq.neurongym.neurongym;

/**
 * Created by infoincarnation on 2/17/2015.
 */

public class RowItem {
    private int imageId;
    private int subImage;
    private int subBadge;
    private String title;
    private String subTitle;

    public RowItem(int imageId, int subImage, int subBadge, String title, String subTitle) {
        this.imageId = imageId;
        this.subImage = subImage;
        this.subBadge = subBadge;
        this.title = title;
        this.subTitle = subTitle;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int subImage) {
        this.imageId = imageId;
    }

    public int getsubImage() {
        return subImage;
    }

    public void setsubImage(int subImage) {
        this.subImage = subImage;
    }

    public int getSubBadge() {
        return subBadge;
    }

    public void setSubBadge(int subBadge) {
        this.subBadge = subBadge;
    }

    public String getDesc() {
        return subTitle;
    }

    public void setDesc(String desc) {
        this.subTitle = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title + "\n" + subTitle;
    }
}
