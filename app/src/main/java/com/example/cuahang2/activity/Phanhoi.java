package com.example.cuahang2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahang2.R;
import com.example.cuahang2.util.CheckConnection;
import com.example.cuahang2.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Phanhoi extends AppCompatActivity {
    Toolbar toolbarphanhoi;
    EditText edttieude,edtnoidung;
    Button btnxacnhanphanhoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phanhoi);
        toolbarphanhoi=(Toolbar)findViewById(R.id.toolbarphanhoi);
        Anhxa();
        ActionToolbar();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }
        else {
            CheckConnection.ShowToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }
    private void EventButton() {
        btnxacnhanphanhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tieude=edttieude.getText().toString().trim();
                final String noidung=edtnoidung.getText().toString().trim();

                CheckConnection.ShowToast_short(getApplicationContext(),"Phản hồi của bạn đã được chúng tôi ghi nhận!");
                Intent i=new Intent(Phanhoi.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
    private void Anhxa() {
        edttieude=(EditText)findViewById(R.id.edittexttieudephanhoi) ;
        edtnoidung=(EditText)findViewById(R.id.edittextnoidungphanhoi) ;
        btnxacnhanphanhoi=(Button)findViewById(R.id.buttonxacnhanphanhoi);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarphanhoi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarphanhoi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
