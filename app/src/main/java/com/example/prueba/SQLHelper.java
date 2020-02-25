package com.example.prueba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    final String CREAR_TABLA_USUARIO="CREATE TABLE usuarios (id integer , nombre text,telefono text )";




    public SQLHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public  void onCreate (SQLiteDatabase db){

            db.execSQL(CREAR_TABLA_USUARIO);


        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);

    }




}
