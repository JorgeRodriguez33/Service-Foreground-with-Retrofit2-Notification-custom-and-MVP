package com.example.servicewhitretrofit2.Models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    @Generated("jsonschema2pojo")
    public class Weather {

        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("code")
        @Expose
        private Integer code;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

    }
