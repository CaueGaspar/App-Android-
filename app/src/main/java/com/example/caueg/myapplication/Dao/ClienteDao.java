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

    public SQLiteDatabase database; // SQLiteDatabase é Responsavel pela manutenção dos dados
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

        ContentValues values = new ContentValues(); // ContentValues classe queecebe os valores que serao enviados para a base de dados

        values.put("nome", c.getNome());  // put recebe o nome do campo da tabela, seguido pelo valor
        values.put("numDoc", c.getNumDoc());
        values.put("email", c.getEmail());
        values.put("endereco", c.getEndereco());
        values.put("telefone", c.getTelefone());
        values.put("senha", c.getSenha());
        values.put("foto", c.getFoto());
        values.put("flagDoc", c.getFlagDoc());

        return getDatabase().insert("cliente", null, values);
    }

    public int atualizar(Cliente c) {
        ContentValues values = new ContentValues(); // ContentValues classe queecebe os valores que serao enviados para a base de dados

        values.put("nome", c.getNome());  // put recebe o nome do campo da tabela, seguido pelo valor
        values.put("numDoc", c.getNumDoc());
        values.put("email", c.getEmail());
        values.put("endereco", c.getEndereco());
        values.put("telefone", c.getTelefone());
        values.put("senha", c.getSenha());
        values.put("foto", c.getFoto());
        values.put("flagDoc", c.getFlagDoc());

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(c.getId());

        return getDatabase().update("Cliente", values, "id = ?", parametros);
    }

    public void excluir(int id) {

        try {
            String delete = "delete from cliente where id =  " + "'" + id + "'" + "";
            String[] parameters = new String[]{String.valueOf(id)};
            parameters[0] = String.valueOf(id);

            getDatabase().delete("cliente", "id = ?", parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Cliente autenticacao(String usuario, String senha) {

        Cliente c = new Cliente();
        String select = "select * from cliente where nome = " + "'" + usuario + "'" + " and senha = " + "'" + senha + "'";
        String[] parameters = new String[]{usuario, senha};
        Cursor cursor = getDatabase().rawQuery(select, null);

        try {
            while (cursor.moveToNext()) {
                c.setId(cursor.getInt(cursor.getColumnIndex("id")));
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
