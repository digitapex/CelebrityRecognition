
package com.spitslide.celebrityrecognition.qwant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filters {

    @SerializedName("size")
    @Expose
    private Size size;
    @SerializedName("license")
    @Expose
    private License license;
    @SerializedName("freshness")
    @Expose
    private Freshness freshness;
    @SerializedName("color")
    @Expose
    private Color color;
    @SerializedName("imagetype")
    @Expose
    private Imagetype imagetype;
    @SerializedName("source")
    @Expose
    private Source source;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public Freshness getFreshness() {
        return freshness;
    }

    public void setFreshness(Freshness freshness) {
        this.freshness = freshness;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Imagetype getImagetype() {
        return imagetype;
    }

    public void setImagetype(Imagetype imagetype) {
        this.imagetype = imagetype;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

}
