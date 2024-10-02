package com.example.mi.primer.myrecetario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<RecetaMasa> recipeList; // Cambiado a RecetaMasa

    // Constructor para pasar la lista de recetas al adaptador
    public RecipeAdapter(List<RecetaMasa> recipeList) { // Cambiado a RecetaMasa
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout para cada elemento del RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false); // Cambiado a simple_list_item_2
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // Asignar la información de la receta a cada elemento
        RecetaMasa recipe = recipeList.get(position);
        holder.recipeTitle.setText(recipe.getNombreDeLaMasa()); // Mostrar el nombre
        holder.recipeInfo.setText(recipe.getInformacion()); // Mostrar otra información de la receta (ajusta según tus atributos)
    }

    @Override
    public int getItemCount() {
        return recipeList.size(); // Número total de elementos en la lista
    }

    // Clase interna que representa el ViewHolder de cada elemento
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTitle;
        public TextView recipeInfo; // Añadido para información adicional

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(android.R.id.text1); // Referencia al TextView para el nombre
            recipeInfo = itemView.findViewById(android.R.id.text2); // Referencia al TextView para la información adicional
        }
    }
}
