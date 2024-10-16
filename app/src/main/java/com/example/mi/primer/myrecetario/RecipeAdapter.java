package com.example.mi.primer.myrecetario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<RecetaMasa> recipeList;
    private double cantidadSeleccionada; // Cantidad seleccionada desde el spinner

    // Constructor modificado para incluir la cantidad seleccionada
    public RecipeAdapter(List<RecetaMasa> recipeList, double cantidadSeleccionada) {
        this.recipeList = recipeList;
        this.cantidadSeleccionada = cantidadSeleccionada; // Guardar la cantidad seleccionada
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout para cada elemento del RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // Obtener la receta actual
        RecetaMasa recipe = recipeList.get(position);

        // Mostrar el nombre de la receta
        holder.recipeTitle.setText(recipe.getNombreDeLaMasa());

        // Mostrar información adicional de la receta
        holder.recipeInfo.setText(recipe.getInformacion());

        // Crear el adapter para los ingredientes ajustados usando la cantidad seleccionada
        IngredientAdapter ingredientAdapter = new IngredientAdapter(recipe.getIngredientesAjustados(cantidadSeleccionada));

        // Configurar el RecyclerView de ingredientes
        holder.recyclerViewIngredients.setAdapter(ingredientAdapter);
        holder.recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    // Clase ViewHolder que gestiona las vistas para cada receta
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTitle;
        public TextView recipeInfo;
        public RecyclerView recyclerViewIngredients; // Para mostrar los ingredientes

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializar las vistas
            recipeTitle = itemView.findViewById(R.id.text1);
            recipeInfo = itemView.findViewById(R.id.text2);
            recyclerViewIngredients = itemView.findViewById(R.id.ingredientsRecyclerView);
        }
    }
}
