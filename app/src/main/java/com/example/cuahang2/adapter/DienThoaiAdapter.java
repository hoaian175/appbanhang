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

public class DienThoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraydienthoai;

    public DienThoaiAdapter(Context context, ArrayList<Sanpham> arraydienthoai) {
        this.context = context;
        this.arraydienthoai = arraydienthoai;
    }

    @Override
    public int getCount() {
        return arraydienthoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arraydienthoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txttendienthoai,txtgiadienthoai,txtmotadienthoai;
        public ImageView imgdienthoai;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_dienthoai,null);
            viewHolder.txttendienthoai=(TextView)view.findViewById(R.id.textviewtendienthoai);
            viewHolder.txtgiadienthoai=(TextView)view.findViewById(R.id.textviewgiadienthoai);
            viewHolder.txtmotadienthoai=(TextView)view.findViewById(R.id.textviewmotadienthoai);
            viewHolder.imgdienthoai=(ImageView)view.findViewById(R.id.imageviewdienthoai);
            view.setTag(viewHolder);
        }
        else
            viewHolder= (ViewHolder) view.getTag();
        Sanpham sanpham= (Sanpham) getItem(position);
        viewHolder.txttendienthoai.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiadienthoai.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" VNĐ");
        viewHolder.txtmotadienthoai.setMaxLines(2);
        viewHolder.txtmotadienthoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotadienthoai.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.loading).error(R.drawable.error).into(viewHolder.imgdienthoai);
        return view;
    }
}
