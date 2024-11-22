package com.example.mi.primer.myrecetario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapterProvisional extends RecyclerView.Adapter<RecipeAdapterProvisional.RecipeViewHolder> {

    private static final String TAG = "RecipeAdapterProvisional";
    private List<Object> recipeList; // Lista de recetas de diferentes tipos

    // Constructor para inicializar la lista de recetas
    public RecipeAdapterProvisional(List<Object> recipeList) {
        this.recipeList = recipeList;
        Log.d(TAG, "RecipeAdapterProvisional inicializado con " + recipeList.size() + " recetas.");
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout para cada elemento en el RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrecipe_tareas_provicional, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // Obtener la receta actual
        Object recipe = recipeList.get(position);

        String nombre = "Nombre no disponible";
        String informacion = "Información no disponible";

        // Determinar el tipo de receta y obtener los datos correspondientes
        if (recipe instanceof RecetaBatidos) {
            RecetaBatidos recetaBatidos = (RecetaBatidos) recipe;
            if (recetaBatidos != null) {
                nombre = recetaBatidos.getNombreDelBatido() != null ? recetaBatidos.getNombreDelBatido() : nombre;
                informacion = recetaBatidos.getInformacion() != null ? recetaBatidos.getInformacion() : informacion;
            }
        } else if (recipe instanceof RecetaMasa) {
            RecetaMasa recetaMasa = (RecetaMasa) recipe;
            if (recetaMasa != null) {
                nombre = recetaMasa.getNombreDeLaMasa() != null ? recetaMasa.getNombreDeLaMasa() : nombre;
                informacion = recetaMasa.getInformacion() != null ? recetaMasa.getInformacion() : informacion;
            }
        } else if (recipe instanceof RecetaGalletas) {
            RecetaGalletas recetaGalletas = (RecetaGalletas) recipe;
            if (recetaGalletas != null) {
                nombre = recetaGalletas.getNombreGalleta() != null ? recetaGalletas.getNombreGalleta() : nombre;
                informacion = recetaGalletas.getInformacion() != null ? recetaGalletas.getInformacion() : informacion;
            }
        } else if (recipe instanceof RecetaPastel) {
            RecetaPastel recetaPastel = (RecetaPastel) recipe;
            if (recetaPastel != null) {
                nombre = recetaPastel.getNombrePastel() != null ? recetaPastel.getNombrePastel() : nombre;
                informacion = recetaPastel.getInformacion() != null ? recetaPastel.getInformacion() : informacion;
            }
        }

        // Establecer el nombre y la información de la receta en las vistas
        holder.recipeName.setText(nombre);
        holder.recipeInfo.setText(informacion);
    }

    @Override
    public int getItemCount() {
        return recipeList != null ? recipeList.size() : 0;
    }

    // ViewHolder para gestionar las vistas individuales en el RecyclerView
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeName;
        public TextView recipeInfo;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName); // Asegúrate de que el ID coincida con el layout
            recipeInfo = itemView.findViewById(R.id.recipeInfo);   // Asegúrate de que el ID coincida con el layout
        }
    }

    // Método para actualizar la lista de recetas y refrescar el RecyclerView
    public void setRecipeList(List<Object> newRecipeList) {
        this.recipeList = newRecipeList;
        Log.d(TAG, "setRecipeList: Lista de recetas actualizada con " + newRecipeList.size() + " elementos.");
        notifyDataSetChanged();
    }
}
