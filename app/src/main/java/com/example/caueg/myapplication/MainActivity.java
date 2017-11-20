package com.example.caueg.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.caueg.myapplication.Dao.ClienteDao;
import com.example.caueg.myapplication.Model.Cliente;

public class MainActivity extends AppCompatActivity {

    private EditText editNome, editDocumento, editEmail, aditTelefone, editEndereco, editSenha, editConfSenha;
    private RadioGroup radioGroup;
    private RadioButton radioRg, radioCpf;
    private Button btEnviar, btCadstrado;
    private ImageView imageFoto;
    private ClienteDao clienteDao;
    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clienteDao = new ClienteDao(this);
        cliente = new Cliente();

        editNome       = findViewById(R.id.nome);
        editDocumento  = findViewById(R.id.ndoc);
        editEmail      = findViewById(R.id.email);
        aditTelefone   = findViewById(R.id.telefone);
        editEndereco   = findViewById(R.id.endereco);
        editSenha      = findViewById(R.id.senha);
        editConfSenha  = findViewById(R.id.confSenha);
        radioCpf       = findViewById(R.id.cpf);
        radioRg        = findViewById(R.id.rg);
        radioGroup     = findViewById(R.id.rgroup);
        radioRg        = findViewById(R.id.rg);
        radioCpf       = findViewById(R.id.cpf);
        btEnviar       = findViewById(R.id.btnEnviar);
        btCadstrado    = findViewById(R.id.btnCadastrado);
        imageFoto      = findViewById(R.id.imgUsu);



        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editNome.getText().toString().equals("")
                        && !editDocumento.getText().toString().equals("")
                        && !editEmail.getText().toString().equals("")
                        && !aditTelefone.getText().toString().equals("")
                        && !editEndereco.getText().toString().equals("")
                        && !editSenha.getText().toString().equals("")
                        && !editConfSenha.getText().toString().equals("")) {

                    cliente.setNome(editNome.getText().toString());
                    cliente.setEmail(editEmail.getText().toString());
                    cliente.setNumDoc(editDocumento.getText().toString());
                    cliente.setEndereco(editEndereco.getText().toString());
                    cliente.setSenha(editSenha.getText().toString());
                    cliente.setTelefone(aditTelefone.getText().toString());

                } else {
                    Toast.makeText(MainActivity.this, "Preecha Todos os campos", Toast.LENGTH_SHORT).show();
                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);

                        if (radioButton.getText().toString().equals(radioCpf)) {
                            cliente.setFlagDoc(0);
                        } else {
                            cliente.setFlagDoc(1);
                        }
                    }
                });


                try {
                    clienteDao.incluir(cliente);
                    limpar();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    Toast.makeText(MainActivity.this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        btCadstrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void limpar() {
        editNome.setText("");
        editSenha.setText("");
        editConfSenha.setText("");
        editEndereco.setText("");
        editEmail.setText("");
        editDocumento.setText("");
        aditTelefone.setText("");
    }
}
