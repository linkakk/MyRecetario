package com.example.mi.primer.myrecetario;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private static final String TAG = "RecipeAdapter";
    private List<Object> recipeList; // Acepta recetas de cualquier tipo
    private double cantidadSeleccionada; // Cantidad seleccionada desde el spinner

    public RecipeAdapter(List<Object> recipeList, double cantidadSeleccionada) {
        this.recipeList = recipeList != null ? recipeList : new ArrayList<>();
        this.cantidadSeleccionada = cantidadSeleccionada;
        Log.d(TAG, "RecipeAdapter inicializado con " + this.recipeList.size() + " recetas y cantidadSeleccionada: " + cantidadSeleccionada);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Object recipe = recipeList.get(position);

        // Verificar el tipo de receta y configurar la vista con la información apropiada
        if (recipe instanceof RecetaMasa) {
            RecetaMasa recetaMasa = (RecetaMasa) recipe;
            holder.recipeTitle.setText(recetaMasa.getNombreDeLaMasa());
            holder.recipeInfo.setText(recetaMasa.getInformacion());
            List<Ingredientes.Ingrediente> ingredientesAjustados = recetaMasa.getIngredientesAjustados(cantidadSeleccionada);
            holder.recyclerViewIngredients.setVisibility(View.VISIBLE); // Mostrar ingredientes
            holder.ingredientAdapter.setIngredients(ingredientesAjustados);
        } else if (recipe instanceof RecetaBatidos) {
            RecetaBatidos recetaBatidos = (RecetaBatidos) recipe;
            holder.recipeTitle.setText(recetaBatidos.getNombreDelBatido());
            holder.recipeInfo.setText(recetaBatidos.getInformacion());
            List<Ingredientes.Ingrediente> ingredientesAjustados = recetaBatidos.getIngredientesAjustados(cantidadSeleccionada);
            holder.recyclerViewIngredients.setVisibility(View.VISIBLE); // Mostrar ingredientes
            holder.ingredientAdapter.setIngredients(ingredientesAjustados);
        } else if (recipe instanceof RecetaGalletas) {
            RecetaGalletas recetaGalletas = (RecetaGalletas) recipe;
            holder.recipeTitle.setText(recetaGalletas.getNombreGalleta());
            holder.recipeInfo.setText(recetaGalletas.getInformacion());
            holder.recyclerViewIngredients.setVisibility(View.GONE); // Ocultar ingredientes
        }else if (recipe instanceof RecetaPastel) {
            RecetaPastel recetaPastel = (RecetaPastel) recipe;
            holder.recipeTitle.setText(recetaPastel.getNombrePastel());
            holder.recipeInfo.setText(recetaPastel.getInformacion());
            List<Ingredientes.Ingrediente> ingredientesAjustados = recetaPastel.getIngredientesAjustados(cantidadSeleccionada);
            holder.recyclerViewIngredients.setVisibility(View.VISIBLE); // Mostrar ingredientes
            holder.ingredientAdapter.setIngredients(ingredientesAjustados);
        }

    }

    @Override
    public int getItemCount() {
        return recipeList != null ? recipeList.size() : 0;
    }

    public void setCantidadSeleccionada(double nuevaCantidadSeleccionada) {
        this.cantidadSeleccionada = nuevaCantidadSeleccionada;
        notifyDataSetChanged();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTitle;
        public TextView recipeInfo;
        public RecyclerView recyclerViewIngredients;
        public IngredientAdapter ingredientAdapter;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.text1);
            recipeInfo = itemView.findViewById(R.id.text2);
            recyclerViewIngredients = itemView.findViewById(R.id.ingredientsRecyclerView);
            recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            ingredientAdapter = new IngredientAdapter(new ArrayList<>());
            recyclerViewIngredients.setAdapter(ingredientAdapter);
        }
    }

    public void setRecipes(List<Object> newRecipeList, double nuevaCantidadSeleccionada) {
        this.recipeList = newRecipeList != null ? newRecipeList : new ArrayList<>();
        this.cantidadSeleccionada = nuevaCantidadSeleccionada;
        notifyDataSetChanged();
    }
}
