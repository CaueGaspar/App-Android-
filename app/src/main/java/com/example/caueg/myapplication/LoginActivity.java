package com.example.caueg.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caueg.myapplication.Dao.ClienteDao;
import com.example.caueg.myapplication.Model.Cliente;

/**
 * Created by caueg on 16/11/2017.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText editNome, editSenha;
    private Button btnLogar;
    private ClienteDao clienteDao;
    private Cliente cliente;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        clienteDao = new ClienteDao(this);
        cliente = new Cliente();


        editNome = findViewById(R.id.usuario);
        editSenha = findViewById(R.id.senha);
        btnLogar = findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editNome.getText().toString().equals("")
                        && !editSenha.getText().toString().equals("")) {


                    try {
                        cliente = clienteDao.autenticacao(editNome.getText().toString(), editSenha.getText().toString());
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    if (cliente.getId() != 0) {
                        Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                        intent.putExtra("cliente", cliente);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Preecha Todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
