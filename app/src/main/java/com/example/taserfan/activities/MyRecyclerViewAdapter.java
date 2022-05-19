package com.example.taserfan.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.taserfan.R;
import com.example.taserfan.objects.Vehiculo;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private List<Vehiculo> vehiculos;
    private LayoutInflater inflater;
    private  View.OnClickListener listener;

    public MyRecyclerViewAdapter (Context context,List<Vehiculo> vehiculos){
        this.vehiculos =vehiculos;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view=inflater.inflate(R.layout.simple_element,parent,false);
        View view=inflater.inflate(R.layout.simple_element,parent,false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    public void  setListener(View.OnClickListener listener){this.listener=listener;}
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        List l = root.list.get(position);
//        String url= Parameters.URL_ICON_PRE+l.weather.get(0).icon+Parameters.URL_ICON_POST;
//        ImageDownloader.DownloadImage(url, holder.icon);
//
        holder.matricula.setText(vehiculos.get(position).getMatricula());
        holder.marca.setText(vehiculos.get(position).getMarca());
        holder.color.setText(vehiculos.get(position).getColor());
        holder.estado.setText(vehiculos.get(position).getEstado());
//
//        Date date= new Date((long) l.dt*1000);
//        //simpleDateFormat.setTimeZoneFormat(TimeZone.getDefault());
//
//        holder.fecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(date));
//        holder.dia.setText(new SimpleDateFormat("EEEE",new Locale("es","ES")).format(date));
//        holder.hora.setText(new SimpleDateFormat("HH:mm").format(date));

    }


    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNewData(List<Vehiculo> vehiculo) {
        this.vehiculos =vehiculo;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
       ImageView icon;
       TextView matricula;
       TextView marca;
       TextView color;
       TextView estado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            matricula=itemView.findViewById(R.id.matricula);
            marca=itemView.findViewById(R.id.marca);
            color=itemView.findViewById(R.id.color);
            estado=itemView.findViewById(R.id.estado);

        }
    }
}
