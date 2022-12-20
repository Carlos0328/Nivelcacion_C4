package com.cm.nivelacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cm.nivelacion.DB.DBFireBase;
import com.cm.nivelacion.Entidades.Producto;

public class Form extends AppCompatActivity {
    private Button btnForm;
    private EditText editNameForm,editDescriptionForm,editPriceForm;
    private DBFireBase dbFireBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form);

        btnForm = (Button) findViewById(R.id.btnForm);
        editNameForm = (EditText) findViewById(R.id.editNameForm);
        editDescriptionForm = (EditText) findViewById(R.id.editDescriptionForm);
        editPriceForm = (EditText) findViewById(R.id.editPriceForm);
        dbFireBase = new DBFireBase();

        Intent intentIN = getIntent();
        if(intentIN.getBooleanExtra("edit",false)){
            editNameForm.setText(intentIN.getStringExtra("name"));
            editPriceForm.setText(intentIN.getStringExtra("price"));
            editDescriptionForm.setText(intentIN.getStringExtra("description"));
        }

        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Producto producto = new Producto(
                        editNameForm.getText().toString(),
                        editDescriptionForm.getText().toString(),
                        Integer.parseInt(editPriceForm.getText().toString()),
                        "",
                        "",
                        ""

                );
                if(intentIN.getBooleanExtra("edit",false)){
                    producto.setId(intentIN.getStringExtra("id"));
                    dbFireBase.updateData(producto);

                }else{
                    dbFireBase.insertData(producto);

                }
                dbFireBase.insertData(producto);
                Intent intent = new Intent(getApplicationContext(),Catalogo.class);
                startActivity(intent);

            }
        });
    }
}