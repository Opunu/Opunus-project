package com.example.minuman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;
    private ArrayList<Menu> menus;
    private  OnItemClickListener mClick;

    public MenuAdapter (Context mcontext, ArrayList<Menu> menuminuman){
        context= mcontext;
        menus= menuminuman;
    }

    public interface OnItemClickListener{
        void onItemclick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mClick=listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_menu,parent,false);

        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menubaru= menus.get(position);
        String Gambarbaru= menubaru.getGambar();
        String Nama= menubaru.getNama();
        String Harga= menubaru.getHarga();
        String Keterangan= menubaru.getKeterangan();

        holder.tvdatanama.setText(Nama);
        holder.tvdataharga.setText(Harga);
        Glide
                .with(context)
                .load(Gambarbaru)
                .centerCrop()
                .into(holder.ivdataimg);
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {

            public ImageView ivdataimg;
            public TextView tvdatanama;
            public TextView tvdataharga;
            public Button btndetail;

        public MenuViewHolder(@NonNull View itemView) {
                super(itemView);
                ivdataimg =itemView.findViewById(R.id.img_menu);
                tvdatanama =itemView.findViewById(R.id.tv_nama);
                tvdataharga =itemView.findViewById(R.id.tv_harga);
                btndetail =itemView.findViewById(R.id.btn_kirim);

            btndetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClick != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mClick.onItemclick(position);
                        }
                    }
                }
            });

        }
    }
}
