package net.leocadio.joao.sistemadeferramentas.activities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.leocadio.joao.sistemadeferramentas.R;

public class ConsultarFerramenta extends AppCompatActivity {

    TextView txtnome_ferramenta, txtfabricante, txtpreco, txtcor, txtreferencia;
    Button btfechar;
    SQLiteDatabase db;
    Cursor c;
    int numreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_ferramenta);

        Bundle b = getIntent().getExtras();
        txtnome_ferramenta = (TextView) findViewById(R.id.txtnome_ferramenta);
        txtfabricante = (TextView) findViewById(R.id.txtfabricante);
        txtpreco = (TextView) findViewById(R.id.txtpreco);
        txtcor = (TextView) findViewById(R.id.txtcor);
        txtreferencia = (TextView) findViewById(R.id.txtreferencia);
        btfechar = (Button) findViewById(R.id.btfechar);
        numreg = b.getInt("numreg");
        db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
        c = db.query("ferramentas", new String[] {"nome_ferramenta", "fabricante", "preco", "cor", "referencia"},
                "numreg = " + numreg, null, null, null,
                null);
        c.moveToFirst();
        txtnome_ferramenta.setText(c.getString(0));
        txtfabricante.setText(c.getString(1));
        txtpreco.setText(c.getString(2));
        txtcor.setText(c.getString(3));
        txtreferencia.setText(c.getString(4));

        btfechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarFerramenta.this.finish();
            }
        });
    }
}
