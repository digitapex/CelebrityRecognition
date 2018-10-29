
package com.spitslide.celebrityrecognition.qwant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cache {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("expiration")
    @Expose
    private Integer expiration;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("mode")
    @Expose
    private Integer mode;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

}
