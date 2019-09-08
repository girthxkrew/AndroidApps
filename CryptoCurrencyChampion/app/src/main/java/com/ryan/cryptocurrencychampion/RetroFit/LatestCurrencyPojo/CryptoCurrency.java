package com.ryan.cryptocurrencychampion.RetroFit.LatestCurrencyPojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoCurrency {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getIdQuery ()
    {
        String ids = "";
        for(int i = 0; i < data.size(); i++)
        {
            if(i == (data.size() - 1))
            {
                ids += data.get(i).getId();
            }
            else
            {
                ids += data.get(i).getId() + ",";
            }
        }
        return ids;
    }

}
