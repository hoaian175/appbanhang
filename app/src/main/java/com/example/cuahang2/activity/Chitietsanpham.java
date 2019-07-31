package com.example.cuahang2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cuahang2.R;
import com.example.cuahang2.model.Giohang;
import com.example.cuahang2.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Chitietsanpham extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txtTen,txtGia,txtMota;
    Spinner spinner;
    Button btnDatmua;
    int Id=0;
    String Tenchitiet="";
    int Giachitiet=0;
    String Hinhanhchitiet="";
    String Motachitiet="";
    int Idloaichitietsanpham=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
    }

    private void EventButton() {
        btnDatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size()>0)
                {
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists=false;
                    for(int i=0;i<MainActivity.manggiohang.size();i++)
                    {
                        if(MainActivity.manggiohang.get(i).getIdsp()==Id)
                        {
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp()+sl);
                            if(MainActivity.manggiohang.get(i).getSoluongsp()>=10)
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            MainActivity.manggiohang.get(i).setGiasp(Giachitiet*MainActivity.manggiohang.get(i).getSoluongsp());
                            exists=true;
                        }
                    }
                    if (exists==false)
                    {
                        int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi=soluong*Giachitiet;
                        MainActivity.manggiohang.add(new Giohang(Id,Tenchitiet,Giamoi,Hinhanhchitiet,soluong));
                    }
                }
                else
                {
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi=soluong*Giachitiet;
                    MainActivity.manggiohang.add(new Giohang(Id,Tenchitiet,Giamoi,Hinhanhchitiet,soluong));
                }
                Intent intent=new Intent(getApplicationContext(), com.example.cuahang2.activity.Giohang.class);
                startActivity(intent);
            }
        });
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
    private void CatchEventSpinner() {
        Integer[] soluong=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInformation() {

        Sanpham sanpham= (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        Id=sanpham.getIdsp();
        Tenchitiet=sanpham.getTensanpham();
        Giachitiet=sanpham.getGiasanpham();
        Hinhanhchitiet=sanpham.getHinhanhsanpham();
        Motachitiet=sanpham.getMotasanpham();
        Idloaichitietsanpham=sanpham.getIdloaisanpham();
        txtTen.setText(Tenchitiet);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtGia.setText("Giá: "+decimalFormat.format(Giachitiet)+" VNĐ");
        txtMota.setText(Motachitiet);
        Picasso.with(getApplicationContext()).load(Hinhanhchitiet).placeholder(R.drawable.loading).error(R.drawable.error).into(imgChitiet);

    }

    private void Anhxa() {
        toolbarChitiet=(Toolbar)findViewById(R.id.toolbarchitietsanpham);
        imgChitiet=(ImageView)findViewById(R.id.imageviewchitietsanpham);
        txtTen=(TextView)findViewById(R.id.textviewtenchitietsanpham);
        txtGia=(TextView)findViewById(R.id.textviewgiachitietsanpham);
        txtMota=(TextView)findViewById(R.id.textviewmotachitietsanpham);
        spinner=(Spinner)findViewById(R.id.spinner);
        btnDatmua=(Button)findViewById(R.id.buttondatmua);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
