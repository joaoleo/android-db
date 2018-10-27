package net.leocadio.joao.sistemadeferramentas.activities;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.leocadio.joao.sistemadeferramentas.R;

public class CadastrarFerramentas extends AppCompatActivity {

    EditText ednome_ferramenta, edfabricante, edpreco, edcor, edreferencia;
    Button btcadastrar, btfechar;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_ferramentas);

        ednome_ferramenta = (EditText) findViewById(R.id.ednome_ferramenta);
        edfabricante = (EditText) findViewById(R.id.edfabricante);
        edpreco = (EditText) findViewById(R.id.edpreco);
        edcor = (EditText) findViewById(R.id.edcor);
        edreferencia = (EditText) findViewById(R.id.edreferencia);
        btcadastrar = (Button) findViewById(R.id.btcadastrar);
        btfechar = (Button) findViewById(R.id.btfechar);

        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome_ferramenta = ednome_ferramenta.getText().toString();
                String fabricante = edfabricante.getText().toString();
                String preco = edpreco.getText().toString();
                String cor = edcor.getText().toString();
                String referencia = edreferencia.getText().toString();
                ContentValues valor = new ContentValues();
                valor.put("nome_ferramenta", nome_ferramenta);
                valor.put("fabricante", fabricante);
                valor.put("preco", Float.parseFloat(preco));
                valor.put("cor", cor);
                valor.put("referencia", referencia);
                db.insert("ferramentas", null, valor);
                AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastrarFerramentas.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Dados cadastrados com sucesso!");
                dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialogo.show();
            }
        });

        btfechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
        } catch(Exception ex) {
            MostraMensagem("Erro " + ex.toString());
        }
    }

    public void MostraMensagem(String str)
    {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastrarFerramentas.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}
