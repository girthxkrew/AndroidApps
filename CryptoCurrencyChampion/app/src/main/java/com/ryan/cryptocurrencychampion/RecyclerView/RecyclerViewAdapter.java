package com.ryan.cryptocurrencychampion.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.ryan.cryptocurrencychampion.R;
import com.ryan.cryptocurrencychampion.RetroFit.AllCryptoCurrencyInfo;
import com.ryan.cryptocurrencychampion.UI.CryptoCurrencyInfo;
import java.text.NumberFormat;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private AllCryptoCurrencyInfo allCryptoCurrencyInfo;
    private Context context;

    public RecyclerViewAdapter(AllCryptoCurrencyInfo allCryptoCurrencyInfo, Context context)
    {
        this.allCryptoCurrencyInfo = allCryptoCurrencyInfo;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name;
        public TextView price;
        public TextView marketCap;
        public TextView volume;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.crypto_currency_name);
            price = itemView.findViewById(R.id.price_in_dollars);
            marketCap = itemView.findViewById(R.id.market_cap);
            volume = itemView.findViewById(R.id.twenty_four_hour_volume);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        viewHolder.name.setText(allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getName());
        if(allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getQuote().getUSD().getPrice() != null)
        {
            viewHolder.price.setText("Price: " +
                    format.format(allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getQuote().getUSD().getPrice()));
        }
        else
        {
            viewHolder.price.setText("Price: " + format.format(0));
        }
        if(allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getQuote().getUSD().getMarketCap() != null)
        {
            viewHolder.marketCap.setText("Market Cap: " +
                    format.format(allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getQuote().getUSD().getMarketCap()));
        }
        else
        {
            viewHolder.marketCap.setText("Market Cap: " + format.format(0));
        }
        if(allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getQuote().getUSD().getVolume24h() != null)
        {
            viewHolder.volume.setText("Volume(24h): " +
                    format.format(allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getQuote().getUSD().getVolume24h()));
        }
        else
        {
            viewHolder.volume.setText("Volume(24h): " + format.format(0));
        }
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CryptoCurrencyInfo.class);
                intent.putExtra("name", allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getName());
                intent.putExtra("logo", allCryptoCurrencyInfo.getMetadata().getData().get(
                        allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getId()).getLogo());
                if(allCryptoCurrencyInfo.getMetadata().getData().get(
                        allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getId()).getUrls().getWebsite().size() > 0)
                {
                    intent.putExtra("website", allCryptoCurrencyInfo.getMetadata().getData().get(
                            allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getId()).getUrls().getWebsite().get(0));
                }
                else
                {
                    intent.putExtra("website", "No website");
                }
                if(allCryptoCurrencyInfo.getMetadata().getData().get(
                        allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getId()).getUrls().getTechnical_doc().size() > 0)
                {
                    intent.putExtra("technical", allCryptoCurrencyInfo.getMetadata().getData().get(
                            allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getId()).getUrls().getTechnical_doc().get(0));
                }
                else
                {
                    intent.putExtra("technical", "No Technical Doc");
                }
                intent.putExtra("description", allCryptoCurrencyInfo.getMetadata().getData().get(
                        allCryptoCurrencyInfo.getCryptoCurrency().getData().get(i).getId()).getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allCryptoCurrencyInfo.getCryptoCurrency().getData().size();
    }

    public void update(AllCryptoCurrencyInfo newInfo)
    {
        final CryptoCurrencyDiffCallback diffCallback = new CryptoCurrencyDiffCallback(allCryptoCurrencyInfo, newInfo);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        allCryptoCurrencyInfo.getCryptoCurrency().getData().clear();
        allCryptoCurrencyInfo.getCryptoCurrency().setData(newInfo.getCryptoCurrency().getData());
        allCryptoCurrencyInfo.getCryptoCurrency().setStatus(newInfo.getCryptoCurrency().getStatus());
        allCryptoCurrencyInfo.getMetadata().getData().clear();
        allCryptoCurrencyInfo.getMetadata().setData(newInfo.getMetadata().getData());
        allCryptoCurrencyInfo.getMetadata().setStatus(newInfo.getMetadata().getStatus());
        diffResult.dispatchUpdatesTo(this);
    }

}

