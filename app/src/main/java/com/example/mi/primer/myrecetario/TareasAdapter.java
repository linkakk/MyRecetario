package com.example.mi.primer.myrecetario;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {

    private List<CreaTuLista> listaTareas;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemDeleteClick(CreaTuLista tarea);
    }

    public TareasAdapter(List<CreaTuLista> listaTareas, OnItemClickListener listener) {
        this.listaTareas = listaTareas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        CreaTuLista tarea = listaTareas.get(position);

        if (tarea != null) {
            String descripcion = tarea.getDescripcion() != null ? tarea.getDescripcion() : "Sin descripción";
            String fechaLimite = tarea.getFechaLimite() != null ? tarea.getFechaLimite() : "Sin fecha";

            if (holder.txtNombreTarea != null) {
                holder.txtNombreTarea.setText(descripcion);
            } else {
                Log.e("TareasAdapter", "txtNombreTarea es null en posición: " + position);
            }

            if (holder.txtFechaLimiteTarea != null) {
                holder.txtFechaLimiteTarea.setText("Fecha límite: " + fechaLimite);
            } else {
                Log.e("TareasAdapter", "txtFechaLimiteTarea es null en posición: " + position);
            }

            if (holder.btnEliminarTarea != null) {
                holder.btnEliminarTarea.setOnClickListener(v -> listener.onItemDeleteClick(tarea));
            } else {
                Log.e("TareasAdapter", "btnEliminarTarea es null en posición: " + position);
            }
        } else {
            Log.e("TareasAdapter", "Tarea nula en posición: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreTarea;
        TextView txtFechaLimiteTarea;
        ImageButton btnEliminarTarea;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreTarea = itemView.findViewById(R.id.txtNombreTarea);
            txtFechaLimiteTarea = itemView.findViewById(R.id.txtFechaLimiteTarea);
            btnEliminarTarea = itemView.findViewById(R.id.btnEliminarTarea);

            if (txtNombreTarea == null) {
                Log.e("TareasAdapter", "Error: txtNombreTarea no se encontró en el diseño.");
            }
            if (txtFechaLimiteTarea == null) {
                Log.e("TareasAdapter", "Error: txtFechaLimiteTarea no se encontró en el diseño.");
            }
            if (btnEliminarTarea == null) {
                Log.e("TareasAdapter", "Error: btnEliminarTarea no se encontró en el diseño.");
            }
        }
    }
}
