package com.example.cuahang2.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuahang2.R;
import com.example.cuahang2.adapter.GiohangAdapter;
import com.example.cuahang2.util.CheckConnection;

import java.text.DecimalFormat;

public class Giohang extends AppCompatActivity {
    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan,btntieptuc;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolbar();
        CheckData();
        EventUtil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size()>0)
                {
                    Intent intent=new Intent(getApplicationContext(),ThongtinKH.class);
                    startActivity(intent);
                }
                else {
                    CheckConnection.ShowToast_short(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm để thanh toán");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Giohang.this);
                builder.setTitle("Xác nhận xoá sản phẩm");
                builder.setMessage("Bạn có chắc muốn xoá sản phẩm này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.manggiohang.size()<=0)
                        {
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EventUtil();
                            if(MainActivity.manggiohang.size()<=0)
                                txtthongbao.setVisibility(View.VISIBLE);
                            else {
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EventUtil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        EventUtil();
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUtil() {
        long tongtien=0;
        for(int i=0;i<MainActivity.manggiohang.size();i++)
        {
            tongtien+=MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+" VNĐ");
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size()<=0)
        {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }
        else
        {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void Anhxa() {
        lvgiohang=(ListView)findViewById(R.id.listviewgiohang);
        txtthongbao=(TextView)findViewById(R.id.textviewthongbao);
        txttongtien=(TextView)findViewById(R.id.textviewtongtien);
        btnthanhtoan=(Button)findViewById(R.id.buttonthanhtoangiohang);
        btntieptuc=(Button)findViewById(R.id.buttontieptucmuahang);
        toolbargiohang=(Toolbar)findViewById(R.id.toolbargiohang) ;
        giohangAdapter=new GiohangAdapter(Giohang.this,MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
    }
}
