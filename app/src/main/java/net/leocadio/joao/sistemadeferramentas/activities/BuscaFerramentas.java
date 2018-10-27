package net.leocadio.joao.sistemadeferramentas.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import net.leocadio.joao.sistemadeferramentas.models.Ferramenta;
import net.leocadio.joao.sistemadeferramentas.adapters.FerramentasAdapter;
import net.leocadio.joao.sistemadeferramentas.R;

import java.util.ArrayList;

public class BuscaFerramentas extends AppCompatActivity {

    Spinner spnopcoes;
    LinearLayout layout_campo_busca;
    EditText edpalavrachave;
    ListView lstresultado_busca;
    Button btbuscar;
    int opcao_busca;
    int opcao_dados;
    int numreg;
    SQLiteDatabase db;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_ferramentas);

        layout_campo_busca = (LinearLayout) findViewById(R.id.layout_campo_busca);
        spnopcoes = (Spinner) findViewById(R.id.spnopcoes);
        edpalavrachave = (EditText) findViewById(R.id.edpalavrachave);
        btbuscar = (Button) findViewById(R.id.btbuscar);
        lstresultado_busca = (ListView) findViewById(R.id.lstresultado_busca);

        Bundle b = getIntent().getExtras();
        opcao_dados = b.getInt("opcao_dados");

        spnopcoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    layout_campo_busca.setVisibility(View.VISIBLE);
                } else {
                    layout_campo_busca.setVisibility(View.INVISIBLE);
                    BuscarTudo();
                }
                opcao_busca = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busca = edpalavrachave.getText().toString();
                switch (opcao_busca) {
                    case 1: BuscaPorNome(busca); break;
                    case 2: BuscaPorFabricante(busca); break;
                    case 3: BuscaPorReferencia(busca); break;
                }
            }
        });

        lstresultado_busca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent telaDados = null;
                switch (opcao_dados) {
                    case 1: telaDados = new Intent(BuscaFerramentas.this, AlterarDados.class);
                        break;
                    case 2: telaDados = new Intent(BuscaFerramentas.this, ConsultarFerramenta.class);
                        break;
                    case 3: telaDados = new Intent(BuscaFerramentas.this, ExcluirFerramenta.class);
                        break;
                }
                c.moveToPosition(position);
                telaDados.putExtra("numreg", c.getInt(0));
                startActivity(telaDados);
                finish();
            }
        });

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
            BuscarTudo();
        } catch(Exception e) {
            MostraMensagem("Erro " + e.toString());
        }
    }

    public void BuscarTudo()
    {
        c = db.query("ferramentas", new String [] {"numreg", "nome_ferramenta", "fabricante", "referencia"},
                null,null,null,null,null);
        c.moveToFirst();
        ArrayList<Ferramenta> ferramentaArray = new ArrayList<Ferramenta>();
        for (int x = 0; x < c.getCount(); x++) {
            Ferramenta ferramenta = new Ferramenta();
            ferramenta.setNome_ferramenta(c.getString(1));
            ferramenta.setFabricante(c.getString(2));
            ferramenta.setReferencia(c.getString(3));
            ferramentaArray.add(ferramenta);
            c.moveToNext();
        }
        lstresultado_busca.setAdapter(new FerramentasAdapter(this, ferramentaArray));
    }

    public void BuscaPorNome(String palavra_chave)
    {
        try {
            c = db.query("ferramentas", new String[] {"numreg", "nome_ferramenta", "fabricante", "referencia"},
                    "nome_ferramenta like '%" + palavra_chave + "%' ", null, null, null, null);
            c.moveToFirst();
            ArrayList<Ferramenta> ferramentaArray = new ArrayList<Ferramenta>();
            if (c.getCount() > 0) {
                for (int x = 0; x < c.getCount(); x++) {
                    Ferramenta ferramenta = new Ferramenta();
                    ferramenta.setNome_ferramenta(c.getString(1));
                    ferramenta.setFabricante(c.getString(2));
                    ferramenta.setReferencia(c.getString(3));
                    ferramentaArray.add(ferramenta);
                    c.moveToNext();
                }
                lstresultado_busca.setAdapter(new FerramentasAdapter(this, ferramentaArray));
            } else {
                MostraMensagem("Nenhum registro foi encontrado!");
            }
        } catch (Exception e) {
            MostraMensagem("Erro " + e.toString());
        }
    }

    public void BuscaPorFabricante(String palavra_chave)
    {
        try {
            c = db.query("ferramentas", new String[] {"numreg", "nome_ferramenta", "fabricante", "referencia"},
                    "fabricante like '%" + palavra_chave + "%' ", null, null, null, null);
            c.moveToFirst();
            ArrayList<Ferramenta> ferramentaArray = new ArrayList<Ferramenta>();
            if (c.getCount() > 0) {
                for (int x = 0; x < c.getCount(); x++) {
                    Ferramenta ferramenta = new Ferramenta();
                    ferramenta.setNome_ferramenta(c.getString(1));
                    ferramenta.setFabricante(c.getString(2));
                    ferramenta.setReferencia(c.getString(3));
                    ferramentaArray.add(ferramenta);
                    c.moveToNext();
                }
                lstresultado_busca.setAdapter(new FerramentasAdapter(this, ferramentaArray));
            } else {
                MostraMensagem("Nenhum registro foi encontrado!");
            }
        } catch (Exception e) {
            MostraMensagem("Erro " + e.toString());
        }
    }

    public void BuscaPorReferencia(String palavra_chave)
    {
        try {
            c = db.query("ferramentas", new String[] {"numreg", "nome_ferramenta", "fabricante", "referencia"},
                    "referencia = \"" + palavra_chave + "\"", null, null, null, null);
            c.moveToFirst();
            ArrayList<Ferramenta> ferramentaArray = new ArrayList<Ferramenta>();
            if (c.getCount() > 0) {
                for (int x = 0; x < c.getCount(); x++) {
                    Ferramenta ferramenta = new Ferramenta();
                    ferramenta.setNome_ferramenta(c.getString(1));
                    ferramenta.setFabricante(c.getString(2));
                    ferramenta.setReferencia(c.getString(3));
                    ferramentaArray.add(ferramenta);
                    c.moveToNext();
                }
                lstresultado_busca.setAdapter(new FerramentasAdapter(this, ferramentaArray));
            } else {
                MostraMensagem("Nenhum registro foi encontrado!");
            }
        } catch (Exception e) {
            MostraMensagem("Erro :" + e.toString());
        }
    }

    public void MostraMensagem(String str)
    {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(BuscaFerramentas.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}
