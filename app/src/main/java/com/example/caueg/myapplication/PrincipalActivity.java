package com.example.caueg.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.caueg.myapplication.Dao.ClienteDao;
import com.example.caueg.myapplication.Model.Cliente;

public class PrincipalActivity extends AppCompatActivity {

    //Declaração de componentes

    private TextView tvNome, tvDocumento, tvEmail, tvTelefone, tvEndereco;
    private Button btnExcluir;
    private ClienteDao clienteDao;
    private Cliente cliente;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Criando objetos pela referencia ( ID )

        clienteDao = new ClienteDao(this);

        tvNome = findViewById(R.id.nome);
        tvEndereco = findViewById(R.id.endereco);
        tvTelefone = findViewById(R.id.telefone);
        tvEmail = findViewById(R.id.email);
        tvDocumento = findViewById(R.id.ndoc);
        btnExcluir = findViewById(R.id.btnExcluir);


        //Recuperando dados do banco e apresentando em um Textview

        cliente = (Cliente) getIntent().getExtras().get("cliente");

        tvEndereco.setText(getString(R.string.ensereco) + " " + cliente.getEndereco());
        tvNome.setText(getString(R.string.nome) + " " + cliente.getNome());
        tvDocumento.setText(getString(R.string.numDoc) + " " + cliente.getNumDoc());
        tvTelefone.setText(getString(R.string.telefone) + " " + cliente.getTelefone());
        tvEmail.setText(getString(R.string.email) + " " + cliente.getEmail());

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mensagem = new AlertDialog.Builder(PrincipalActivity.this);
                mensagem.setTitle("Excluir");
                mensagem.setMessage("Deseja excluir este item?");


                mensagem.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clienteDao.excluir(cliente.getId());
                        dialog.dismiss();
                    }
                }).create();

                mensagem.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                }).create();

                dialog = mensagem.create();
                dialog.show();
            }

        });


    }


}
