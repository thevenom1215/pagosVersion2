package com.example.thevenom1215.prueba;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thevenom1215.prueba.tablasDTO.Datos;


public class Pagos extends  Activity  {

    Button btncancelar;
    TextView etcvecontrato, etsaldotot, etnomtitular, etparcialidad, etpaquete, etcosto;
    Double SaldoTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagos2);
        btncancelar = (Button)findViewById(R.id.btncancelarp);
        etcvecontrato = (TextView)findViewById(R.id.etcvecontratop);
        etsaldotot = (TextView)findViewById(R.id.etsaldotot);
        etnomtitular = (TextView)findViewById(R.id.etnomTitular);
        etparcialidad = (TextView)findViewById(R.id.etparcialidad);
        etpaquete = (TextView)findViewById(R.id.etpaquete);
        etcosto  = (TextView)findViewById(R.id.etcosto);




        Bundle b = getIntent().getExtras();
        String []values = b.getStringArray("keyArray");
        SaldoTotal = b.getDouble("keySaldo");
        String Saltotot = Double.toString(SaldoTotal);


        etcvecontrato.setText(values[0]);
        etnomtitular.setText(values[2]);
        etpaquete.setText(values[6]);
        etparcialidad.setText(values[7]);
        etsaldotot.setText(Saltotot);
        etcosto.setText(values[9]);


    }


    public void Cancelar(View view) {
        finish();
        Log.v("error","Actividad finalizada");
    }
}
