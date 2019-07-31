package com.example.cuahang2.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahang2.R;
import com.example.cuahang2.adapter.LoaispAdapter;
import com.example.cuahang2.adapter.SanphamAdapter;
import com.example.cuahang2.model.Giohang;
import com.example.cuahang2.model.Loaisp;
import com.example.cuahang2.model.Sanpham;
import com.example.cuahang2.util.CheckConnection;
import com.example.cuahang2.util.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView lvManhinhchinh;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    public static ArrayList<Giohang> manggiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            trustEveryone();
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaiSp();
            GetDuLieuSPMoiNhat();
            CatchOnItemListView();
        }
        else
        {
            CheckConnection.ShowToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối Internet");
            finish();
        }

    }

    @Override
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

    private void CatchOnItemListView() {
        lvManhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        Intent intent=new Intent(MainActivity.this,MainActivity.class );
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        Intent intent1=new Intent(MainActivity.this,DienThoaiActivity.class );
                        intent1.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        Intent intent2=new Intent(MainActivity.this,LapTopActivity.class );
                        intent2.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        Intent intent3=new Intent(MainActivity.this,LienHeActivity.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        Intent intent4=new Intent(MainActivity.this,ThongTinActivity.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        Intent intent5=new Intent(MainActivity.this,Phanhoi.class);
                        startActivity(intent5);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

            }
        });
    }

    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Server.duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    int ID=0;
                    String Tensanpham="";
                    int Giasanpham=0;
                    String Hinhanhsanpham="";
                    String Motasanpham="";
                    int IDloaisanpham=0;
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            ID=jsonObject.getInt("idsp");
                            Tensanpham=jsonObject.getString("tensanpham");
                            Giasanpham=jsonObject.getInt("giasanpham");
                            Hinhanhsanpham=jsonObject.getString("hinhanhsanpham");
                            Motasanpham=jsonObject.getString("motasanpham");
                            IDloaisanpham=jsonObject.getInt("idloaisanpham");
                            mangsanpham.add(new Sanpham(ID,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,IDloaisanpham));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }
    private void GetDuLieuLoaiSp() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Server.duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            tenloaisp=jsonObject.getString("tenloaisanpham");
                            hinhanhloaisp=jsonObject.getString("hinhanhloaisanpham");
                            mangloaisp.add(new Loaisp(id,tenloaisp,hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(3,new Loaisp(0,"Liên Hệ","https://cdn2.iconfinder.com/data/icons/social-media-network-fill-flat-icon/512/Viber-2-512.png"));
                    mangloaisp.add(4,new Loaisp(0,"Thông Tin","https://cdn.pixabay.com/photo/2016/06/15/15/02/info-1459077_960_720.png"));
                    mangloaisp.add(5,new Loaisp(0,"Phản Hồi","https://www.pngkey.com/png/detail/11-113921_phone-icon-in-a-circle-phone-icon-png.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_short(getApplicationContext(),error.toString()+"sai rồi");
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void Anhxa()
    {
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        toolbar=(Toolbar)findViewById(R.id.tbManhinhchinh) ;
        viewFlipper=(ViewFlipper)findViewById(R.id.viewflipper);
        recyclerViewmanhinhchinh=(RecyclerView)findViewById(R.id.recycleViewmanhinhchinh);
        lvManhinhchinh=(ListView)findViewById(R.id.lvManhinhchinh);
        mangloaisp=new ArrayList<>();
        mangloaisp.add(0,new Loaisp(0,"Trang Chính","https://img.icons8.com/plasticine/2x/home-page.png"));
        loaispAdapter=new LoaispAdapter(mangloaisp,getApplicationContext());
        lvManhinhchinh.setAdapter(loaispAdapter);
        mangsanpham=new ArrayList<>();
        sanphamAdapter=new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);
        if(manggiohang!=null)
        {

        }
        else
            manggiohang=new ArrayList<>();
    }
    private void ActionViewFlipper() {
        ArrayList<String>mangquangcao=new ArrayList<>();
        mangquangcao.add("https://i.ytimg.com/vi/AUZ3AQD19Q8/maxresdefault.jpg");
        mangquangcao.add("https://advertisingvietnam.com/wp-content/uploads/2018/03/tiki-min.png");
        mangquangcao.add("https://i.ytimg.com/vi/3B-53z916DY/maxresdefault.jpg");
        mangquangcao.add("https://images.kienthuc.net.vn/zoomh/500/uploaded/nguyenvan/2016_12_07/Ho/ngam-ho-ngoc-ha-cuc-quyen-ru-khi-quang-cao-dien-thoai-Hinh-3.jpg");
        for(int i=0;i<mangquangcao.size();i++)
        {
            ImageView imageView=new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_to_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    public void ActionBar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}
