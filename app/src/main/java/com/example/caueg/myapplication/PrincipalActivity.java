package com.example.caueg.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caueg.myapplication.Dao.ClienteDao;
import com.example.caueg.myapplication.Model.Cliente;

public class PrincipalActivity extends AppCompatActivity {

    //Declaração de componentes

    private TextView tvNome, tvDocumento, tvEmail, tvTelefone, tvEndereco;
    private ClienteDao clienteDao;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Criando objetos pela referencia ( ID )

        tvNome = findViewById(R.id.nome);
        tvEndereco = findViewById(R.id.endereco);
        tvTelefone = findViewById(R.id.telefone);
        tvEmail = findViewById(R.id.email);
        tvDocumento = findViewById(R.id.ndoc);


        //Recuperando dados do banco e apresentando em um Textview

        cliente = (Cliente) getIntent().getExtras().get("cliente");

        tvEndereco.setText(getString(R.string.ensereco) + " " + cliente.getEndereco());
        tvNome.setText(getString(R.string.nome) + " " + cliente.getNome());
        tvDocumento.setText(getString(R.string.numDoc) + " " + cliente.getNumDoc());
        tvTelefone.setText(getString(R.string.telefone) + " " + cliente.getTelefone());
        tvEmail.setText(getString(R.string.email) + " " + cliente.getEmail());
    }
}
