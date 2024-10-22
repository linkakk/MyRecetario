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

    private static final String TAG = "RecipeAdapter"; // Tag para Logcat
    private List<RecetaMasa> recipeList;
    private double cantidadSeleccionada; // Cantidad seleccionada desde el spinner

    // Constructor modificado para incluir la cantidad seleccionada
    public RecipeAdapter(List<RecetaMasa> recipeList, double cantidadSeleccionada) {
        this.recipeList = recipeList;
        this.cantidadSeleccionada = cantidadSeleccionada; // Guardar la cantidad seleccionada
        Log.d(TAG, "RecipeAdapter inicializado con " + recipeList.size() + " recetas y cantidadSeleccionada: " + cantidadSeleccionada);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout para cada elemento del RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        Log.d(TAG, "onCreateViewHolder: Inflando nuevo ViewHolder para receta");
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // Obtener la receta actual
        RecetaMasa recipe = recipeList.get(position);
        Log.d(TAG, "onBindViewHolder: Receta " + position + " - " + recipe.getNombreDeLaMasa());

        // Mostrar el nombre de la receta
        holder.recipeTitle.setText(recipe.getNombreDeLaMasa());

        // Mostrar información adicional de la receta
        holder.recipeInfo.setText(recipe.getInformacion());

        // Obtener los ingredientes ajustados usando la cantidad seleccionada
        List<Ingredientes.Ingrediente> ingredientesAjustados = recipe.getIngredientesAjustados(cantidadSeleccionada);
        Log.d(TAG, "Receta " + recipe.getNombreDeLaMasa() + " - Ingredientes ajustados: " + ingredientesAjustados.size());

        // Actualizar el adaptador de ingredientes
        holder.ingredientAdapter.setIngredients(ingredientesAjustados);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: Número de recetas: " + recipeList.size());
        return recipeList.size(); // Número total de elementos en la lista
    }

    // Método para actualizar la cantidad seleccionada y refrescar el adaptador
    public void setCantidadSeleccionada(double nuevaCantidadSeleccionada) {
        this.cantidadSeleccionada = nuevaCantidadSeleccionada;
        Log.d(TAG, "setCantidadSeleccionada: Nueva cantidad seleccionada: " + nuevaCantidadSeleccionada);
        notifyDataSetChanged(); // Refrescar el RecyclerView
    }

    // Clase ViewHolder que gestiona las vistas para cada receta
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTitle;
        public TextView recipeInfo;
        public RecyclerView recyclerViewIngredients; // Para mostrar los ingredientes
        public IngredientAdapter ingredientAdapter;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializar las vistas
            recipeTitle = itemView.findViewById(R.id.text1); // Asegúrate de que el ID coincida
            recipeInfo = itemView.findViewById(R.id.text2); // Asegúrate de que el ID coincida
            recyclerViewIngredients = itemView.findViewById(R.id.ingredientsRecyclerView); // Asegúrate de que el ID coincida

            // Configurar el RecyclerView de ingredientes
            recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            ingredientAdapter = new IngredientAdapter(new ArrayList<>());
            recyclerViewIngredients.setAdapter(ingredientAdapter);

            Log.d(TAG, "RecipeViewHolder: ViewHolder creado y adaptador de ingredientes configurado");
        }
    }

    // Método para actualizar la lista de recetas
    public void setRecipes(List<RecetaMasa> newRecipeList, double nuevaCantidadSeleccionada) {
        this.recipeList = newRecipeList;
        this.cantidadSeleccionada = nuevaCantidadSeleccionada;
        Log.d(TAG, "setRecipes: Lista de recetas actualizada con " + newRecipeList.size() + " recetas y cantidadSeleccionada: " + nuevaCantidadSeleccionada);
        notifyDataSetChanged();
    }
}
