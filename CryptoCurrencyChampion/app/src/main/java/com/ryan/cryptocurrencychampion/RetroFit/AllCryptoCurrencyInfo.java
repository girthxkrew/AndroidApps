package com.ryan.cryptocurrencychampion.RetroFit;

import com.ryan.cryptocurrencychampion.RetroFit.LatestCurrencyPojo.CryptoCurrency;
import com.ryan.cryptocurrencychampion.RetroFit.MetadataPojo.Metadata;

public class AllCryptoCurrencyInfo {
    private Metadata metadata;
    private CryptoCurrency cryptoCurrency;

    public AllCryptoCurrencyInfo(Metadata metadata, CryptoCurrency cryptoCurrency) {
        this.metadata = metadata;
        this.cryptoCurrency = cryptoCurrency;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }
}
