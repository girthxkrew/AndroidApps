package com.ryan.cryptocurrencychampion.RecyclerView;

import androidx.recyclerview.widget.DiffUtil;

import com.ryan.cryptocurrencychampion.RetroFit.AllCryptoCurrencyInfo;
import com.ryan.cryptocurrencychampion.RetroFit.LatestCurrencyPojo.CryptoCurrency;
import com.ryan.cryptocurrencychampion.RetroFit.LatestCurrencyPojo.Datum;

public class CryptoCurrencyDiffCallback extends DiffUtil.Callback {
    private AllCryptoCurrencyInfo oldInfo;
    private AllCryptoCurrencyInfo newInfo;

    public CryptoCurrencyDiffCallback(AllCryptoCurrencyInfo oldInfo, AllCryptoCurrencyInfo newInfo) {
        this.oldInfo = oldInfo;
        this.newInfo = newInfo;
    }

    @Override
    public int getOldListSize() {
        return oldInfo.getCryptoCurrency().getData().size();
    }

    @Override
    public int getNewListSize() {
        return newInfo.getCryptoCurrency().getData().size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldInfo.getCryptoCurrency().getData().get(oldItemPosition).getId()
                .equals(newInfo.getCryptoCurrency().getData().get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Datum oldData = oldInfo.getCryptoCurrency().getData().get(oldItemPosition);
        final Datum newData = newInfo.getCryptoCurrency().getData().get(newItemPosition);
        return oldData.getName().equals(newData.getName());
    }
}
