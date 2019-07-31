package com.example.cuahang2.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahang2.R;
import com.example.cuahang2.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraylaptop;

    public LaptopAdapter(Context context, ArrayList<Sanpham> arraylaptop) {
        this.context = context;
        this.arraylaptop = arraylaptop;
    }

    @Override
    public int getCount() {
        return arraylaptop.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylaptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txttenlaptop,txtgialaptop,txtmotalaptop;
        public ImageView imglaptop;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_laptop,null);
            viewHolder.txttenlaptop=(TextView)view.findViewById(R.id.textviewtenlaptop);
            viewHolder.txtgialaptop=(TextView)view.findViewById(R.id.textviewgialaptop);
            viewHolder.txtmotalaptop=(TextView)view.findViewById(R.id.textviewmotalaptop);
            viewHolder.imglaptop=(ImageView)view.findViewById(R.id.imageviewlaptop);
            view.setTag(viewHolder);
        }
        else
            viewHolder= (ViewHolder) view.getTag();
        Sanpham sanpham= (Sanpham) getItem(position);
        viewHolder.txttenlaptop.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgialaptop.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" VNĐ");
        viewHolder.txtmotalaptop.setMaxLines(2);
        viewHolder.txtmotalaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotalaptop.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.loading).error(R.drawable.error).into(viewHolder.imglaptop);
        return view;
    }
}
