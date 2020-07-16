package com.example.vinhntph08047_lab3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Links {
    @SerializedName("self")
    @Expose
    List<Self> list;

    public Links(List<Self> list) {
        this.list = list;
    }

    public class Self {
        @SerializedName("href")
        @Expose
        String href;

        public Self(String href) {
            this.href = href;
        }
    }
}
