package net.leocadio.joao.sistemadeferramentas;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterarDadosActivity extends AppCompatActivity {

    EditText ednome_ferramenta, edfabricante, edpreco, edcor, edreferencia;
    Button btalterar, btfechar;
    SQLiteDatabase db;
    int numreg;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados);

        Bundle b = getIntent().getExtras();
        ednome_ferramenta = (EditText) findViewById(R.id.ednome_ferramenta);
        edfabricante = (EditText) findViewById(R.id.edfabricante);
        edpreco = (EditText) findViewById(R.id.edpreco);
        edcor = (EditText) findViewById(R.id.edcor);
        edreferencia = (EditText) findViewById(R.id.edreferencia);
        btalterar = (Button) findViewById(R.id.btalterar);
        btfechar = (Button) findViewById(R.id.btfechar);
        numreg = b.getInt("numreg");
        db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);

        c = db.query("ferramentas", new String[] {
                        "nome_ferramenta", "fabricante", "preco","cor",
                        "referencia" },"numreg = " + numreg, null, null, null,
                null);
        c.moveToFirst();
        ednome_ferramenta.setText(c.getString(0));
        edfabricante.setText(c.getString(1));
        edpreco.setText(c.getString(2));
        edcor.setText(c.getString(3));
        edreferencia.setText(c.getString(4));

        btalterar.setOnClickListener(new View.OnClickListener() {
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
                db.update("ferramentas", valor, "numreg=" + numreg,null);

                AlertDialog.Builder dialogo = new AlertDialog.Builder(AlterarDadosActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Dados atualizados com sucesso!");
                dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlterarDadosActivity.this.finish();
                    }
                });
                dialogo.show();
            }
        });
        btfechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlterarDadosActivity.this.finish();
            }
        });
    }
}
