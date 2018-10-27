package net.leocadio.joao.sistemadeferramentas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.leocadio.joao.sistemadeferramentas.R;
import net.leocadio.joao.sistemadeferramentas.models.Ferramenta;

import java.util.ArrayList;

public class FerramentasAdapter extends BaseAdapter {

    private static ArrayList<Ferramenta> ferramentaArrayList;
    private LayoutInflater mInflater;
    public FerramentasAdapter(Context context, ArrayList<Ferramenta> ferramenta)
    {
        ferramentaArrayList = ferramenta;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ferramentaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return ferramentaArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate (R.layout.list_ferramentas, null);
            holder = new ViewHolder();
            holder.txtnomeferramenta = (TextView) convertView.findViewById(R.id.txtnome_ferramenta);
            holder.txtfabricante = (TextView) convertView.findViewById(R.id.txtfabricante);
            holder.txtreferencia = (TextView) convertView.findViewById(R.id.txtreferencia);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtnomeferramenta.setText(ferramentaArrayList.get(position).getNome_ferramenta());
        holder.txtfabricante.setText(ferramentaArrayList.get(position).getFabricante());
        holder.txtreferencia.setText(ferramentaArrayList.get(position).getReferencia());
        return convertView;
    }

    static class ViewHolder
    {
        TextView txtnomeferramenta;
        TextView txtfabricante;
        TextView txtreferencia;
    }

}
