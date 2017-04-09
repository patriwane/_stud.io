package net.pacee.studio.Utils;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.pacee.studio.R;
import net.pacee.studio.model.Interro;
import net.pacee.studio.model.Matiere;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class InterroAdapter extends RecyclerView.Adapter<InterroAdapter.ListViewHolder> {

    Context context;
    List<Interro> interros;
    CustomAdapter.OnMatiereClickListener listener;
    public void setListener(CustomAdapter.OnMatiereClickListener listener)
    {
        this.listener = listener;
    }

    public InterroAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.interros_list,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {


            holder.nom.setText(interros.get(position).getNom());
//            System.out.println(interros.get(position).getNom());
            Object o = interros.get(position).getNote();
            holder.notes.setText((o!=null?o:0)+"");

    }

    @Override
    public int getItemCount() {
        if(interros ==null)
        return 0;

        return interros.size();
    }


    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView nom;
        TextView notes;
        public ListViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.tv_interros_title);
            notes = (TextView) itemView.findViewById(R.id.tv_interros_notes);
            itemView.findViewById(R.id.ll_interros_layout).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           
            listener.getPosition(getAdapterPosition());
        }
    }

    public void setInterros(List<Interro> interros)
    {

        this.interros = interros;
        this.notifyDataSetChanged();
    }


}
