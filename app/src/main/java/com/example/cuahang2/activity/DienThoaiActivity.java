package com.example.cuahang2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahang2.R;
import com.example.cuahang2.adapter.DienThoaiAdapter;
import com.example.cuahang2.model.Sanpham;
import com.example.cuahang2.util.CheckConnection;
import com.example.cuahang2.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienThoaiActivity extends AppCompatActivity {
    Toolbar toolbardt;
    ListView lvdt;
    DienThoaiAdapter dienThoaiAdapter;
    ArrayList<Sanpham> mangdt;
    int iddt=0;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            GetIdLoaiSP();
            ActionToolbar();
            GetData(page);
        }else{
            CheckConnection.ShowToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại Internet");
            finish();
        }
        lvdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),Chitietsanpham.class);
                intent.putExtra("thongtinsanpham",mangdt.get(position));
                startActivity(intent);
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.duongdandienthoai+String.valueOf(Page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int Id=0;
                String Tendt="";
                int Giadt=0;
                String Hinhanhdt="";
                String Motadt="";
                int Idloaisp=0;
                if(response!=null)
                {
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i =0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            Id=jsonObject.getInt("idsp");
                            Tendt=jsonObject.getString("tensanpham");
                            Giadt=jsonObject.getInt("giasanpham");
                            Hinhanhdt=jsonObject.getString("hinhanhsanpham");
                            Motadt=jsonObject.getString("motasanpham");
                            Idloaisp=jsonObject.getInt("idloaisanpham");
                            mangdt.add(new Sanpham(Id,Tendt,Giadt,Hinhanhdt,Motadt,Idloaisp));
                            dienThoaiAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String, String>();
                param.put("idsanpham",String.valueOf(iddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menugiohang:
                Intent intent=new Intent(getApplicationContext(), com.example.cuahang2.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbardt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdLoaiSP() {
        iddt=getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("giatriloaisanpham",iddt+"");
    }

    private void Anhxa() {
        toolbardt=(Toolbar) findViewById(R.id.toolbardienthoai);
        lvdt=(ListView)findViewById(R.id.listviewdienthoai);
        mangdt=new ArrayList<>();
        dienThoaiAdapter=new DienThoaiAdapter(getApplicationContext(),mangdt);
        lvdt.setAdapter(dienThoaiAdapter);
    }
}
