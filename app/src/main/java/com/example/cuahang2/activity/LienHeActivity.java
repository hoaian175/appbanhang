package com.example.cuahang2.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.cuahang2.R;

public class LienHeActivity extends AppCompatActivity {
    Toolbar toolbarlh;
    ImageButton imgbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        toolbarlh=findViewById(R.id.toolbarlienhe);
        imgbtn=findViewById(R.id.imagebuttonfb);
        ActionToolbar();
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl ( "https://www.facebook.com/hoaian6767/");
            }
        });
    }
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent WebView = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(WebView);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarlh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
