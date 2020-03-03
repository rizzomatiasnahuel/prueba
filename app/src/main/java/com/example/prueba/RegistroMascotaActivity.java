package com.example.prueba;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prueba.Utilidades.Utilidades;
import com.example.prueba.entidades.Usuario;

import java.util.ArrayList;


public class RegistroMascotaActivity extends AppCompatActivity {

    EditText razaMascota,nombreMascota;
    Spinner comboDuenio;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        razaMascota= (EditText) findViewById(R.id.campoRaza);
        nombreMascota= (EditText) findViewById(R.id.campoNombreMascota);
        comboDuenio= (Spinner) findViewById(R.id.comboDuenioMascota);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        consultarListaPersonas();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaPersonas);

        comboDuenio.setAdapter(adaptador);



        comboDuenio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void registrarMascota() {

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA,nombreMascota.getText().toString());
        values.put(Utilidades.CAMPO_RAZA_MASCOTA,razaMascota.getText().toString());

        int idCombo= (int) comboDuenio.getSelectedItemId();
        if (idCombo!=0){
            Log.i("TAMAÑO",personasList.size()+"");
            Log.i("id combo",idCombo+"");
            Log.i("id combo - 1",(idCombo-1)+"");
            int idDuenio=personasList.get(idCombo-1).getId();
            Log.i("id DUEÑO",idDuenio+"");

            values.put(Utilidades.CAMPO_ID_DUENIO,idDuenio);

            Long idResultante=db.insert(Utilidades.TABLA_MASCOTA,Utilidades.CAMPO_ID_MASCOTA,values);

            Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
            db.close();

        }else{
            Toast.makeText(getApplicationContext(),"Debe seleccionar un Dueño",Toast.LENGTH_LONG).show();
        }


    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario persona=null;
        personasList =new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona=new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            Log.i("Tel",persona.getTelefono());

            personasList.add(persona);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId()+" - "+personasList.get(i).getNombre());
        }

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegistroMascota: registrarMascota();
        }
    }


}
