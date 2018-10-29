
package com.spitslide.celebrityrecognition.qwant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
