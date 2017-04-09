package net.pacee.studio.Utils;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.pacee.studio.R;
import net.pacee.studio.model.Matiere;

import java.io.Serializable;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ListViewHolder> {

    Context context;
    List<Matiere> matieres;
    OnMatiereClickListener listener;
    public void setListener(OnMatiereClickListener listener)
    {
        this.listener = listener;
    }
    public interface OnMatiereClickListener
    {
        public void getPosition(int position);
    }
    public CustomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.matieres_list,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Serializable serializable = matieres.get(position);
        if(serializable instanceof Matiere)
            holder.textList.setText((matieres.get(position)).getNom());
    }

    @Override
    public int getItemCount() {
        if(matieres==null)
        return 0;

        return matieres.size();
    }


    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView textList;
        public ListViewHolder(View itemView) {
            super(itemView);
            textList = (TextView) itemView.findViewById(R.id.tv_main_title);
            textList.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.getPosition(getAdapterPosition());
        }
    }

    public void setMatieres(List<Matiere> matieres)
    {
        this.matieres = matieres;
        this.notifyDataSetChanged();
    }


}
