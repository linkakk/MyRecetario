package com.example.mi.primer.myrecetario;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecipeAdapterProvisional extends RecyclerView.Adapter<RecipeAdapterProvisional.RecipeViewHolder> {

    private static final String TAG = "RecipeAdapterProvisional";
    private List<RecetaBatidos> tortasList; // Lista de recetas de tortas

    // Constructor para inicializar la lista de recetas
    public RecipeAdapterProvisional(List<RecetaBatidos> tortasList) {
        this.tortasList = tortasList;
        Log.d(TAG, "RecipeAdapterProvisional inicializado con " + tortasList.size() + " recetas de tortas.");
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout para cada elemento en el RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // Obtener la receta de torta actual
        RecetaBatidos torta = tortasList.get(position);

        // Establecer el nombre y la información de la receta
        String nombre = torta.getNombreDelBatido() != null ? torta.getNombreDelBatido() : "Nombre no disponible";
        String informacion = torta.getInformacion() != null ? torta.getInformacion() : "Información no disponible";

        holder.recipeTitle.setText(nombre);
        holder.recipeInfo.setText(informacion);
    }

    @Override
    public int getItemCount() {
        return tortasList.size();
    }

    // ViewHolder para gestionar las vistas individuales en el RecyclerView
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTitle;
        public TextView recipeInfo;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.text1); // Asegúrate de que el ID coincida con el layout
            recipeInfo = itemView.findViewById(R.id.text2); // Asegúrate de que el ID coincida con el layout
        }
    }

    // Método para actualizar la lista de recetas de tortas y refrescar el RecyclerView
    public void setTortasList(List<RecetaBatidos> newTortasList) {
        this.tortasList = newTortasList;
        Log.d(TAG, "setTortasList: Lista de tortas actualizada con " + newTortasList.size() + " recetas.");
        notifyDataSetChanged();
    }
}
