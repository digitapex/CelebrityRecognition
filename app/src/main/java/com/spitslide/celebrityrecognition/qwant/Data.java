
package com.spitslide.celebrityrecognition.qwant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("query")
    @Expose
    private Query query;
    @SerializedName("cache")
    @Expose
    private Cache cache;
    @SerializedName("result")
    @Expose
    private Result result;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
