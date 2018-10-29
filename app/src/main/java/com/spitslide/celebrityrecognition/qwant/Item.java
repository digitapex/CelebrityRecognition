
package com.spitslide.celebrityrecognition.qwant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("media")
    @Expose
    private String media;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("thumb_width")
    @Expose
    private Integer thumbWidth;
    @SerializedName("thumb_height")
    @Expose
    private Integer thumbHeight;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("b_id")
    @Expose
    private String bId;
    @SerializedName("media_fullsize")
    @Expose
    private String mediaFullsize;
    @SerializedName("thumb_type")
    @Expose
    private String thumbType;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("media_preview")
    @Expose
    private String mediaPreview;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getThumbWidth() {
        return thumbWidth;
    }

    public void setThumbWidth(Integer thumbWidth) {
        this.thumbWidth = thumbWidth;
    }

    public Integer getThumbHeight() {
        return thumbHeight;
    }

    public void setThumbHeight(Integer thumbHeight) {
        this.thumbHeight = thumbHeight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBId() {
        return bId;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

    public String getMediaFullsize() {
        return mediaFullsize;
    }

    public void setMediaFullsize(String mediaFullsize) {
        this.mediaFullsize = mediaFullsize;
    }

    public String getThumbType() {
        return thumbType;
    }

    public void setThumbType(String thumbType) {
        this.thumbType = thumbType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMediaPreview() {
        return mediaPreview;
    }

    public void setMediaPreview(String mediaPreview) {
        this.mediaPreview = mediaPreview;
    }

}
