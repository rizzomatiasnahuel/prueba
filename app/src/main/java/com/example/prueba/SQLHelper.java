package com.example.prueba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHerlper extends SQLiteOpenHelper {

        public  SQLHerlper(Context context, String name, SQLiteOpenHelper.CursorFactory  factory, int version){
            super(context, name, version);


        }
    @Override
    public  void onCreate (SQLiteDatabase sqLiteDatabase){



        }
    @Override
    public  void onUpgrade (SQLiteDatabase sqLiteDatabase, Int versionAntigua, int il){



    }


}
