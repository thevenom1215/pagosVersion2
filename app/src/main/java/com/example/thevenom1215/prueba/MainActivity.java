package com.example.thevenom1215.prueba;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.thevenom1215.prueba.tablasDTO.Datos;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    //<editor-fold desc="Variables">
    EditText etnumcontrato, etnombre, etappat, etapmat, etcalle, etcolonia, etcvecontratop;
    Button btnbuscar, btnlimpiar, btnhistorial, btnpagar;
    RadioButton rbtncontrato, rbtntitular, rbtndomicilio;
    RadioGroup rg1;
    SQLiteOpenHelper ayuda;
    SQLiteDatabase database;
    Cursor cursor;
    ListView lvrecibos;
    String sql, cveContrato, NO_RECIBO, Nombre, NO_PARCIAL, ap_pat, ap_mat, desc, pago_parcial, no_parcial;
    ArrayAdapter ladaptable;
    View SelectedRadio;
    Double SALDO_TOT;
    int Satus_pago;
    String[] values = new String[10];
    Datos datosprospecto;
    //</editor-fold>

    final ArrayList<String> datos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibos);

        etnumcontrato = (EditText) findViewById(R.id.etnumcontrato);
        etnombre = (EditText) findViewById(R.id.etnombre);
        etappat = (EditText) findViewById(R.id.etappat);
        etapmat = (EditText) findViewById(R.id.etapmat);
        etcalle = (EditText) findViewById(R.id.etcalle);
        etcolonia = (EditText) findViewById(R.id.etcolonia);

        etcvecontratop=(EditText) findViewById(R.id.etcvecontratop);

        btnbuscar = (Button) findViewById(R.id.btnbuscar);
        btnlimpiar = (Button) findViewById(R.id.btnlimpiar);
        btnhistorial = (Button) findViewById(R.id.btnhitorial);
        btnpagar = (Button) findViewById(R.id.btnpagar);

        rbtncontrato = (RadioButton) findViewById(R.id.rbtncontrato);
        rbtntitular = (RadioButton) findViewById(R.id.rbtntitular);
        rbtndomicilio = (RadioButton) findViewById(R.id.rbtndomicilio);
        SelectedRadio = findViewById(R.id.rbtncontrato);

        rg1 = (RadioGroup) findViewById(R.id.radioGroup);
        rg1.setOnCheckedChangeListener(this);

        lvrecibos = (ListView) findViewById(R.id.lvrecibos);

        ladaptable = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);

        ayuda = new recibos(this, "recibos.db", null, 1);
        database = ayuda.getWritableDatabase();

        //<editor-fold desc="ListView Click">
        lvrecibos.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {


                try {
                    String CVE_Contrato = (String) parent.getItemAtPosition(pos);

                    String []split = CVE_Contrato.split(",");

                    datosprospecto = new Datos();
                    datosprospecto.setCvecontrato(split[0]);
                    datosprospecto.setNO_RECIBO(split[1]);
                    datosprospecto.setNombre(split[2]);
                    datosprospecto.setNumerodeparcialidades(split[3]);
                    datosprospecto.setSALDO_TOTAL(Double.parseDouble(split[4]));
                    datosprospecto.setStatuspago(Integer.parseInt((split[5])));
                    datosprospecto.setAP_PATSOLICIT(split[6]);
                    datosprospecto.setAP_MATSOLICIT(split[7]);
                    datosprospecto.setDESCRIPCION(split[8]);
                    datosprospecto.setNO_PARCIALIDAD(split[9]);
                    datosprospecto.setPAGO_PARCIALIDAD(split[10]);
                    datosprospecto.setTOT_APAG(split[11]);

                    Log.v("DATOS", "El dato es : "+datosprospecto.getPAGO_PARCIALIDAD());

                    values[0] = datosprospecto.getCvecontrato();
                    values[1] = datosprospecto.getNO_RECIBO();
                    values[2] = datosprospecto.getNombre();
                    values[3] = datosprospecto.getNumerodeparcialidades();
                    SALDO_TOT = datosprospecto.getSALDO_TOTAL();
                    Satus_pago = datosprospecto.getStatuspago();
                    values[4] = datosprospecto.getAP_PATSOLICIT();
                    values[5] = datosprospecto.getAP_MATSOLICIT();
                    values[6] = datosprospecto.getDESCRIPCION();
                    values[7] = datosprospecto.getNO_PARCIALIDAD();
                    values[8] = datosprospecto.getPAGO_PARCIALIDAD();
                    values[9] = datosprospecto.getTOT_APAG();



                    Log.v("Cesar", "cvecontrato :"+cveContrato);

                } catch (Exception e) {
                    Log.v("verbose", "exception "+ e.getMessage());
                }
            }
        });
        //</editor-fold>

    }

    public void onRadioButtonClicked(View view) {
        SelectedRadio = view;
    }

    public void Pagar (View v) {
        if (values !=null) {
            try {
                Intent i = new Intent(MainActivity.this, Pagos.class);
                i.putExtra("keyArray", values);
                i.putExtra("keySaldo", SALDO_TOT);
                i.putExtra("keysatus", Satus_pago);
                startActivity(i);

                etnumcontrato.setText("");
                etnombre.setText("");
                etappat.setText("");
                etapmat.setText("");
                etcalle.setText("");
                etcolonia.setText("");
                datos.clear();
                rg1.clearCheck();


            } catch (Exception e) {
                Log.v("error", "exception" + e.getMessage());
            }

        }else {
            Toast.makeText(getApplicationContext(),"Algun Campo esta vacio",Toast.LENGTH_LONG).show();
        }
    }

    public void Nuevo(View view) {
        Date fecha = new Date(26072016);
        SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yyyy");

        if (lvrecibos.getCount() <= 0) {
            abre.setID_RECIBO(1);
            abre.setSTS_PAGO(1);
            abre.setCVE_CONTR(etnumcontrato.getText().toString());
            abre.setFEC_GENREC(formato.format(fecha));
        }

        else{
            for (int i = 0; i < lvrecibos.getSelectedItem().toString().length(); i++) {
                if (lvrecibos.getSelectedItem().toString().charAt(i) == ' ') {
                    x = i;
                    break;
                } else {
                    numeroContrato = numeroContrato + lvrecibos.getSelectedItem().toString().charAt(i);
                }
            }

            Toast.makeText(view.getContext(), "numero de contrato: " + numeroContrato, Toast.LENGTH_LONG).show();

            sql = "select max(ID_RECIBO),STS_PAGO,CVE_CONTR,FEC_GENREC from MFRF_M_CONTPREV_D_PAGOS where CVE_CONTR = '" + numeroContrato + "' and ID_CONTR = CVE_CONTR ";
            cursor = database.rawQuery(sql, null);
            abre.setID_RECIBO(cursor.getInt(0) + 1);
            abre.setSTS_PAGO(Integer.parseInt(cursor.getString(1)));
            abre.setCVE_CONTR(cursor.getString(2));
            abre.setFEC_GENREC(cursor.getString(3));
        }
        Intent abrir = new Intent(this,Pagos.class);
        startActivity(abrir);
    }

    public void Limpiar(View view) {

        etnumcontrato.setText("");
        etnombre.setText("");
        etappat.setText("");
        etapmat.setText("");
        etcalle.setText("");
        etcolonia.setText("");
        datos.clear();
    }

    public void Buscar (View arg0) {
        try {
            switch (SelectedRadio.getId()) {

                case R.id.rbtncontrato:
                    Log.d("DEBUG", "Busqueda por Contrato");
                    datos.clear();

                   /* sql="  select b.ID_CONTR, r.ID_RECIBO , a.NOM_SOLICIT,r.NO_PARCIAL,r.SDO_TOTAL, r.STS_PAGO from MFRF_M_SOLICITANTES a, " +
                            "MFRF_M_CONTPREV_H b, MFRF_M_CONTPREV_D_RECGEN_2 r where a.ID_SOLICIT = b.ID_SOLICITANTE and b.ID_CONTR = " +
                            "r.ID_CONTR and r.ID_CONTR = b.ID_CONTR and b.CVE_CONTR = '" +etnumcontrato.getText().toString()+"' and r.STS_PAGO in ('1','10','12')";*/

                    sql="select b.CVE_CONTR, r.NO_RECIBO , a.NOM_SOLICIT ,r.NO_PARCIAL ,r.SDO_TOTAL, r.STS_PAGO ,a.AP_PATSOLICIT,a.AP_MATSOLICIT, " +
                            "f.DESCRIPCION, b.NO_PARCIALID , b.PAGO_PARCIAL, b.TOT_APAG from MFRF_M_SOLICITANTES a, MFRF_M_CONTPREV_H b, MFRF_M_CONTPREV_D_RECGEN_2 r," +
                            "C_PAQUETE f , C_PARCIALIDADES g, MFRF_C_COLONIAS c where b.CVE_CONTR = '"+etnumcontrato.getText().toString() + "' and r.STS_PAGO in ('1','10','11','12')" +
                            "and c.ID_COLONIA = a.ID_COLONIA  and f.ID_PAQUETE = b.ID_PAQUETE and g.ID_PARCIALIDAD = b.ID_PARCIAL AND a.ID_SOLICIT = b.ID_SOLICITANTE  ";


                    cursor = database.rawQuery(sql, null);
                                        int i = 0;
                    while (cursor.moveToNext()) {
                        datos.add(i, cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2) + "," +  cursor.getString(3) + ","+ cursor.getString(4)+ ","+
                                cursor.getString(5)+ ","+ cursor.getString(6)+ ","+ cursor.getString(7)+ ","+ cursor.getString(8)+ ","+ cursor.getString(9)+ ","+ cursor.getString(10)+ ","+ cursor.getString(11));
                        i++;
                    }

                    lvrecibos.setAdapter(ladaptable);



                    etnombre.setText("");
                    etappat.setText("");
                    etapmat.setText("");
                    etcalle.setText("");
                    etcolonia.setText("");


                    break;

                case R.id.rbtntitular:
                    datos.clear();
                    sql="select b.CVE_CONTR, r.NO_RECIBO , a.NOM_SOLICIT ,r.NO_PARCIAL ,r.SDO_TOTAL, r.STS_PAGO ,a.AP_PATSOLICIT,a.AP_MATSOLICIT, " +
                            "f.DESCRIPCION, b.NO_PARCIALID , b.PAGO_PARCIAL, b.TOT_APAG from MFRF_M_SOLICITANTES a, MFRF_M_CONTPREV_H b, MFRF_M_CONTPREV_D_RECGEN_2 r," +
                            "C_PAQUETE f , C_PARCIALIDADES g, MFRF_C_COLONIAS c where a.NOM_SOLICIT = '"+ etnombre.getText().toString() +"'and a.AP_PATSOLICIT = '"+ etappat.getText().toString() +"' " +
                            "and a.AP_MATSOLICIT = '"+ etapmat.getText().toString() +"' and r.STS_PAGO in ('1','10','11','12') AND a.ID_SOLICIT = b.ID_SOLICITANTE  and c.ID_COLONIA = a.ID_COLONIA  and f.ID_PAQUETE = b.ID_PAQUETE " +
                            "and g.ID_PARCIALIDAD = b.ID_PARCIAL ";


                    cursor = database.rawQuery(sql, null);
                    i = 0;
                    while (cursor.moveToNext()) {
                        datos.add(i, cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2) + "," +  cursor.getString(3) + ","+ cursor.getString(4)+ ","+
                                cursor.getString(5)+ ","+ cursor.getString(6)+ ","+ cursor.getString(7)+ ","+ cursor.getString(8)+ ","+ cursor.getString(9)+ ","+ cursor.getString(10)+ ","+ cursor.getString(11));
                        i++;
                    }

                    lvrecibos.setAdapter(ladaptable);
                    etnumcontrato.setText("");
                    etcalle.setText("");
                    etcolonia.setText("");


                    break;

                case R.id.rbtndomicilio:
                    datos.clear();
                    sql="select b.CVE_CONTR, r.NO_RECIBO , a.NOM_SOLICIT ,r.NO_PARCIAL ,r.SDO_TOTAL, r.STS_PAGO ,a.AP_PATSOLICIT,a.AP_MATSOLICIT, " +
                            "f.DESCRIPCION, b.NO_PARCIALID , b.PAGO_PARCIAL, b.TOT_APAG from MFRF_M_SOLICITANTES a, MFRF_M_CONTPREV_H b, MFRF_M_CONTPREV_D_RECGEN_2 r," +
                            "C_PAQUETE f , C_PARCIALIDADES g, MFRF_C_COLONIAS c where c.DES_COLONIA like '"+ etcolonia.getText().toString() +"'and a.CALLE_1 like '"+ etcalle.getText().toString() +"'  " +
                            "and r.STS_PAGO in ('1','10','11','12') AND a.ID_SOLICIT = b.ID_SOLICITANTE  and c.ID_COLONIA = a.ID_COLONIA  and f.ID_PAQUETE = b.ID_PAQUETE " +
                            "and g.ID_PARCIALIDAD = b.ID_PARCIAL ";


                    cursor = database.rawQuery(sql, null);
                    i = 0;
                    while (cursor.moveToNext()) {
                        datos.add(i, cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2) + "," +  cursor.getString(3) + ","+ cursor.getString(4)+ ","+
                                cursor.getString(5)+ ","+ cursor.getString(6)+ ","+ cursor.getString(7)+ ","+ cursor.getString(8)+ ","+ cursor.getString(9)+ ","+ cursor.getString(10)+ ","+ cursor.getString(11));
                        i++;
                    }

                    lvrecibos.setAdapter(ladaptable);
                    etnumcontrato.setText("");
                    etnombre.setText("");
                    etappat.setText("");
                    etapmat.setText("");




                    break;


            }
        } catch (SQLiteException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();{
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}

