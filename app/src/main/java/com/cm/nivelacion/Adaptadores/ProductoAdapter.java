package com.cm.nivelacion.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cm.nivelacion.Catalogo;
import com.cm.nivelacion.DB.DBFireBase;
import com.cm.nivelacion.Entidades.Producto;
import com.cm.nivelacion.Form;
import com.cm.nivelacion.R;
import com.cm.nivelacion.info;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> arrayProductos;

    public ProductoAdapter(Context context, ArrayList<Producto> arrayProductos) {
        this.context = context;
        this.arrayProductos = arrayProductos;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Producto> getArrayProductos() {
        return arrayProductos;
    }

    public void setArrayProductos(ArrayList<Producto> arrayProductos) {
        this.arrayProductos = arrayProductos;
    }

    @Override
    public int getCount() {
        return arrayProductos.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayProductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.producto_template, null);
        Producto producto = arrayProductos.get(i);
        ImageView imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
        TextView textNameProduct = (TextView) view.findViewById(R.id.textNameProduct);
        TextView textDescriptionProduct = (TextView) view.findViewById(R.id.textDescriptionProduct);
        TextView textPriceProduct = (TextView) view.findViewById(R.id.textPriceProduct);
        Button btnDeleteProduct = (Button) view.findViewById(R.id.btnDeleteProduct);
        Button btnEditProduct = (Button) view.findViewById(R.id.btnEditProduct);

        imgProduct.setImageResource(R.drawable.blanca);

        textNameProduct.setText(producto.getName());
        textDescriptionProduct.setText(producto.getDescription());
        textPriceProduct.setText(String.valueOf(producto.getPrice()));

        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, info.class);
               intent.putExtra("name",producto.getName());
               intent.putExtra("description",producto.getDescription());
               intent.putExtra("price",String.valueOf(producto.getPrice()));

                context.startActivity(intent);

            }
        });

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBFireBase dbFireBase = new DBFireBase();
                dbFireBase.deleteData(producto.getId());
                Intent intent = new Intent(context, Catalogo.class);
                context.startActivity(intent);


            }
        });
        btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Form.class);
                intent.putExtra("edit",true);
                intent.putExtra("id",producto.getName());
                intent.putExtra("name",producto.getName());
                intent.putExtra("description",producto.getDescription());
                intent.putExtra("price",String.valueOf(producto.getPrice()));
                intent.putExtra("image",producto.getImage());
                intent.putExtra("latitud",producto.getLatitud());
                intent.putExtra("Longitud",producto.getLongitud());
                context.startActivity(intent);

            }
        });
        return view;
    }
}
