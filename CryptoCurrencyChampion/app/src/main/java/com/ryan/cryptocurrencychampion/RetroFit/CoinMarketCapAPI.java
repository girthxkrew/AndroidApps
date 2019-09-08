package com.ryan.cryptocurrencychampion.RetroFit;

import com.ryan.cryptocurrencychampion.RetroFit.LatestCurrencyPojo.CryptoCurrency;
import com.ryan.cryptocurrencychampion.RetroFit.MetadataPojo.Metadata;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CoinMarketCapAPI {

    @Headers({
            "X-CMC_PRO_API_KEY: fc9df45c-8401-4192-80a2-8220dfebf8df",
            "Accept: application/json"
    })
    @GET("cryptocurrency/listings/latest?limit=50&convert=USD&start=1")
    Observable<CryptoCurrency> getLatest(@Query("sort") String toSort);

    @Headers({
            "X-CMC_PRO_API_KEY: fc9df45c-8401-4192-80a2-8220dfebf8df",
            "Accept: application/json"
    })
    @GET("cryptocurrency/info")
    Observable<Metadata> getMetaData(@Query("id") String id);
}
