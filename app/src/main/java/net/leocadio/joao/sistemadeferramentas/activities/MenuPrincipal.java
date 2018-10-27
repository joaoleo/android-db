package net.leocadio.joao.sistemadeferramentas.activities;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.leocadio.joao.sistemadeferramentas.R;

public class MenuPrincipal extends AppCompatActivity {

    SQLiteDatabase db;

    Button btcadastrar_ferramenta;
    Button btconsultar_ferramenta;
    Button btalterar_dados;
    Button btexcluir_ferramenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        btcadastrar_ferramenta = (Button) findViewById(R.id.btcadastrar_ferramenta);
        btalterar_dados = (Button) findViewById(R.id.btalterar_dados);
        btconsultar_ferramenta = (Button) findViewById(R.id.btconsultar_ferramenta);
        btexcluir_ferramenta = (Button) findViewById(R.id.btexcluir_ferramenta);

        btcadastrar_ferramenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrarFerramenta = new Intent(MenuPrincipal.this, CadastrarFerramentas.class);
                startActivity(cadastrarFerramenta);
            }
        });

        btalterar_dados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultaFerramenta = new Intent(MenuPrincipal.this, BuscaFerramentas.class);
                consultaFerramenta.putExtra("opcao_dados", 1);
                startActivity(consultaFerramenta);
            }
        });

        btconsultar_ferramenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultaFerramenta = new Intent(MenuPrincipal.this, BuscaFerramentas.class);
                consultaFerramenta.putExtra("opcao_dados", 2);
                startActivity(consultaFerramenta);
            }
        });

        btexcluir_ferramenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultaFerramenta = new Intent(MenuPrincipal.this, BuscaFerramentas.class);
                consultaFerramenta.putExtra("opcao_dados", 3);
                startActivity(consultaFerramenta);
            }
        });

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
            db.execSQL("create table if not exists " +
                    "ferramentas(numreg integer primary key" +
                    " autoincrement, nome_ferramenta text not null, "
                    + "fabricante text not null," + "preco float not null, " +
                    "cor text not null, referencia text not null)");
        } catch (Exception ex) {
            MostraMensagem("Erro " + ex.toString());
        }
    }

    public void MostraMensagem(String str)
    {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MenuPrincipal.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}
