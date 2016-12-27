package com.example.cristian.mamaandroidthermalpos.recycleViewProductos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cristian.mamaandroidthermalpos.FragmentVentas;
import com.example.cristian.mamaandroidthermalpos.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by Cristian on 16/12/2016.
 */

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>{
    private List<Producto> items;

    public static class ProductoViewHolder extends RecyclerView.ViewHolder{

        public TextView txtNombreProducto,
                txtPrecioProducto,
                txtCantidadProducto;

        public Button btnBorrarProducto,
                btnCantidadMas,
                btnCantidadMenos;

        public ProductoViewHolder(View v){
            super(v);
            txtNombreProducto = (TextView) v.findViewById(R.id.txtNombreProducto);
            txtPrecioProducto = (TextView) v.findViewById(R.id.txtPrecioProducto);
            txtCantidadProducto = (TextView) v.findViewById(R.id.txtCantidadProducto);

            btnCantidadMas = (Button) v.findViewById(R.id.btnCantidadMas);
            btnCantidadMenos = (Button) v.findViewById(R.id.btnCantidadMenos);
            btnBorrarProducto = (Button) v.findViewById(R.id.btnBorrarProducto);
        }



    }


    public ProductoAdapter(List<Producto> items){
        this.items = items;
    }




    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_card_layout,parent,false);
        return new ProductoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ProductoViewHolder holder, final int position) {
        holder.txtNombreProducto.setText(items.get(position).getNombre());
        String cantidad = "x"+items.get(position).getCantidadS();
        holder.txtCantidadProducto.setText(cantidad);


        //Se hace a parte para prevenir Warnings
        String calculo = String.format(Locale.getDefault(),"%.2f", items.get(position).getPrecio() * items.get(position).getCantidad())+"€" ;
        holder.txtPrecioProducto.setText(calculo);


        holder.btnCantidadMas.setText(R.string.btnMas);
        holder.btnCantidadMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.get(position).setCantidad(items.get(position).getCantidad() +1 );
                notifyItemChanged(position);

            }
        });

        holder.btnCantidadMenos.setText(R.string.btnMenos);
        holder.btnCantidadMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(items.get(position).getCantidad() > 1 ){
                    items.get(position).setCantidad(items.get(position).getCantidad() -1 );
                    notifyItemChanged(position);

                }
            }
        });

        holder.btnBorrarProducto.setText(R.string.btnBorrarProd);
        holder.btnBorrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(position);
                notifyDataSetChanged();
                notifyItemRemoved(position);
            }
        });
    }




    @Override
    public int getItemCount() {
        return items.size();
    }


}


