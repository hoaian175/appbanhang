package com.example.cuahang2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahang2.R;
import com.example.cuahang2.activity.MainActivity;
import com.example.cuahang2.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arraygiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder
    {
        public TextView txttengiohang,txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus,btnvalues,btnplus;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang=(TextView) view.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang=(TextView) view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang=(ImageView)view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus=(Button)view.findViewById(R.id.buttonminus);
            viewHolder.btnvalues=(Button)view.findViewById(R.id.buttonvalues);
            viewHolder.btnplus=(Button)view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)view.getTag();
        }
        Giohang giohang= (Giohang) getItem(position);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp())+" VNĐ");
        Picasso.with(context).load(giohang.getHinhsp()).placeholder(R.drawable.loading).error(R.drawable.error).into(viewHolder.imggiohang);
        viewHolder.btnvalues.setText(giohang.getSoluongsp()+"");
        int sl=Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if(sl>=10)
        {
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        else if(sl<=1)
        {
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }
        else if(sl>=1)
        {
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btnvalues.getText().toString())+1;
                int slhientai= MainActivity.manggiohang.get(position).getSoluongsp();
                long giahientai=MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoinhat);
                long giamoinhat=(giahientai*slmoinhat)/slhientai;
                MainActivity.manggiohang.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat)+" VNĐ");
                com.example.cuahang2.activity.Giohang.EventUtil();
                if(slmoinhat>9)
                {
                    finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btnvalues.getText().toString())-1;
                int slhientai= MainActivity.manggiohang.get(position).getSoluongsp();
                long giahientai=MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoinhat);
                long giamoinhat=(giahientai*slmoinhat)/slhientai;
                MainActivity.manggiohang.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat)+" VNĐ");
                com.example.cuahang2.activity.Giohang.EventUtil();
                if(slmoinhat<2)
                {
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}
