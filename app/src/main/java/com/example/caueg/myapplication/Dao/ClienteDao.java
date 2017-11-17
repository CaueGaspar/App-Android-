package com.example.caueg.myapplication.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.caueg.myapplication.Model.Cliente;

/**
 * Created by caueg on 16/11/2017.
 */

public class ClienteDao {

    private SQLiteDatabase database;
    private DataBaseHelper helper;

    public ClienteDao(Context context) {
        helper = new DataBaseHelper(context);
    }

    public SQLiteDatabase getDatabase() {

        if (database == null)
            database = helper.getWritableDatabase();

        return database;
    }

    public void close() {
        helper.close();
        if (database != null && database.isOpen())
            database.close();
    }

    public long incluir(Cliente c) {

        ContentValues values = new ContentValues();

        values.put("nome", c.getNome());
        values.put("numDoc", c.getNumDoc());
        values.put("email", c.getEmail());
        values.put("endereco", c.getEndereco());
        values.put("telefone", c.getTelefone());
        values.put("senha", c.getSenha());
        values.put("foto", c.getFoto());
        values.put("flagDoc", c.getFlagDoc());

        return getDatabase().insert("cliente", null, values);
    }

    public Cliente autenticacao(String usuario, String senha) {
        Cliente c = new Cliente();

        String select = "select * from cliente";

        String[] parameters = new String[]{usuario, senha};

        Cursor cursor = getDatabase().rawQuery(select, null);

        //Cursor cursor = getDatabase().query("cliente", new String[]{"nome", "telefone", "endereco", "numDoc", "email"},
        //"nome = ? and senha = ?", new String[]{usuario, senha}, null, null, null);

        try {
            while (cursor.moveToNext()) {
                c.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                c.setTelefone(cursor.getString((cursor.getColumnIndex("telefone"))));
                c.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                c.setNumDoc(cursor.getString(cursor.getColumnIndex("numDoc")));
                c.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return c;
    }
}
