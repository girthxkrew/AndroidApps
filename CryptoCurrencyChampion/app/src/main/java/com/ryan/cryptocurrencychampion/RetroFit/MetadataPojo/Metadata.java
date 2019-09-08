package com.ryan.cryptocurrencychampion.RetroFit.MetadataPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ryan.cryptocurrencychampion.RetroFit.LatestCurrencyPojo.Status;

import java.util.HashMap;

public class Metadata {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private HashMap<String, Data> data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public HashMap<String, Data> getData() {
        return data;
    }

    public void setData(HashMap<String, Data> data) {
        this.data = data;
    }

}