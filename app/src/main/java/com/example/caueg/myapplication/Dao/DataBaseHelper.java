package com.example.caueg.myapplication.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by caueg on 16/11/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{
      private static final String BANCO_DADOS = "MyApplication";
      private static final int VERSAO = 1;


    public DataBaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS cliente(id INTEGER PRIMARY KEY, nome TEXT, numDoc TEXT, email TEXT, endereco TEXT, telefone TEXT, senha TEXT," +
                " foto BLOB, flagDoc INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

