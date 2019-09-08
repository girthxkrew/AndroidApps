package com.ryan.cryptocurrencychampion.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.ryan.cryptocurrencychampion.R;
import com.ryan.cryptocurrencychampion.RecyclerView.RecyclerViewAdapter;
import com.ryan.cryptocurrencychampion.RetroFit.AllCryptoCurrencyInfo;
import com.ryan.cryptocurrencychampion.RetroFit.CoinMarketCapAPI;
import com.ryan.cryptocurrencychampion.RetroFit.LatestCurrencyPojo.CryptoCurrency;
import com.ryan.cryptocurrencychampion.RetroFit.MetadataPojo.Metadata;
import com.ryan.cryptocurrencychampion.RetroFit.RetroFitInstance;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private CoinMarketCapAPI handler;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isConnected())
        {
            showDialog();
        }
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        callEndpoints("market_cap");
    }

    private void callEndpoints(String s) {

        if(!isConnected())
        {
            showDialog();
        }

        else
        {
            handler = RetroFitInstance.getRetrofitInstance().create(CoinMarketCapAPI.class);
            Observable<CryptoCurrency> observable = handler.getLatest(s);
            observable
                    .flatMap(new Function<CryptoCurrency, Observable<Metadata>>() {
                        @Override
                        public Observable<Metadata> apply(CryptoCurrency cryptoCurrency) throws Exception {
                            return handler.getMetaData(cryptoCurrency.getIdQuery());
                        }
                    }, new BiFunction<CryptoCurrency, Metadata, AllCryptoCurrencyInfo>() {
                        @Override
                        public AllCryptoCurrencyInfo apply(CryptoCurrency cryptoCurrency, Metadata metadata) throws Exception {
                            return new AllCryptoCurrencyInfo(metadata, cryptoCurrency);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResults, this::handleError);
        }
    }


    private void handleResults(AllCryptoCurrencyInfo allCryptoCurrencyInfo) {
        if (allCryptoCurrencyInfo.getCryptoCurrency().getData() != null
                && allCryptoCurrencyInfo.getCryptoCurrency().getData().size() != 0) {
            if (recyclerViewAdapter == null)
            {
                recyclerViewAdapter = new RecyclerViewAdapter(allCryptoCurrencyInfo, this);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
            else
            {
                recyclerViewAdapter.update(allCryptoCurrencyInfo);
            }
        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void handleError(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.sort)
        {
            showSortDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        String [] options = {"Market Cap", "Name", "Price", "Date Added", "24 Hour Volume"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setIcon(R.drawable.ic_action_sort);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i)
                {
                    case 0:
                        callEndpoints("market_cap");
                        break;
                    case 1:
                        callEndpoints("name");
                        break;
                    case 2:
                        callEndpoints("price");
                        break;
                    case 3:
                        callEndpoints("date_added");
                        break;
                    case 4:
                        callEndpoints("volume_24h");
                        break;
                }
            }
        });
        builder.create().show();
    }

    private boolean isConnected() {
        boolean isConnectedWifi = false;
        boolean isConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    isConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    isConnectedMobile = true;
        }
        return isConnectedWifi || isConnectedMobile;
    }

    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Connect to wifi or quit")
                .setCancelable(false)
                .setPositiveButton("Connect to WIFI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
