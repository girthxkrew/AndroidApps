package com.ryan.cryptocurrencychampion.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ryan.cryptocurrencychampion.R;

public class CryptoCurrencyInfo extends AppCompatActivity {
    private ImageView logo;
    private TextView name;
    private TextView website;
    private TextView technicalDoc;
    private TextView description;
    private String logoPath, nameCC, websiteCC, technicalDocCC, descriptionCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentInfo();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(nameCC);
        }
        setContentView(R.layout.activity_crypto_currency_info);
        logo = findViewById(R.id.logo);
        name = findViewById(R.id.name);
        website = findViewById(R.id.website);
        technicalDoc = findViewById(R.id.technicaldoc);
        description = findViewById(R.id.description);
        name.setText(nameCC);
        if(!websiteCC.equals("No website"))
        {
            website.setClickable(true);
            website.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='" + websiteCC + "'> Website </a>";
            website.setText(Html.fromHtml(text));
        }
        else
        {
            website.setVisibility(View.INVISIBLE);
        }
        if(!technicalDocCC.equals("No Technical Doc"))
        {
            technicalDoc.setClickable(true);
            technicalDoc.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='" + technicalDocCC + "'> Technical Documentation </a>";
            technicalDoc.setText(Html.fromHtml(text));
        }
        else
        {
            technicalDoc.setVisibility(View.INVISIBLE);
        }
        description.setText(descriptionCC);
        Uri uri = Uri.parse(logoPath.replace("64x64", "128x128"));
        Glide.with(this).load(uri).into(logo);
    }

    private void getIntentInfo()
    {
        if(getIntent().hasExtra("name") && getIntent().hasExtra("logo")
                && getIntent().hasExtra("website") && getIntent().hasExtra("technical")
                && getIntent().hasExtra("description"))
        {
            nameCC = getIntent().getStringExtra("name");
            websiteCC = getIntent().getStringExtra("website");
            technicalDocCC = getIntent().getStringExtra("technical");
            descriptionCC = getIntent().getStringExtra("description");
            logoPath = getIntent().getStringExtra("logo");
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
