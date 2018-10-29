
package com.spitslide.celebrityrecognition.qwant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value__ {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("translate")
    @Expose
    private Boolean translate;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getTranslate() {
        return translate;
    }

    public void setTranslate(Boolean translate) {
        this.translate = translate;
    }

}
