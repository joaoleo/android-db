package net.leocadio.joao.sistemadeferramentas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomRowBaseAdapter extends BaseAdapter {

    private static ArrayList<DadosFerramenta> dadosFerramentaArrayList;
    private LayoutInflater mInflater;
    public MyCustomRowBaseAdapter(Context context, ArrayList<DadosFerramenta> dadosFerramenta)
    {
        dadosFerramentaArrayList = dadosFerramenta;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dadosFerramentaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dadosFerramentaArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate (R.layout.custom_row_lisview, null);
            holder = new ViewHolder();
            holder.txtnomeferramenta = (TextView) convertView.findViewById(R.id.txtnome_ferramenta);
            holder.txtfabricante = (TextView) convertView.findViewById(R.id.txtfabricante);
            holder.txtreferencia = (TextView) convertView.findViewById(R.id.txtreferencia);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtnomeferramenta.setText(dadosFerramentaArrayList.get(position).nome_ferramenta);
        holder.txtfabricante.setText(dadosFerramentaArrayList.get(position).fabricante);
        holder.txtreferencia.setText(dadosFerramentaArrayList.get(position).referencia);
        return convertView;
    }

    static class ViewHolder
    {
        TextView txtnomeferramenta;
        TextView txtfabricante;
        TextView txtreferencia;
    }

}
