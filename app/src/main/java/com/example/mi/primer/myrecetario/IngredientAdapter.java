package com.example.mi.primer.myrecetario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredientes.Ingrediente> ingredientList; // Cambia el tipo según tu clase Ingrediente

    // Constructor para pasar la lista de ingredientes al adaptador
    public IngredientAdapter(List<Ingredientes.Ingrediente> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout para cada elemento del RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        // Asignar la información del ingrediente a cada elemento
        Ingredientes.Ingrediente ingredient = ingredientList.get(position);
        holder.ingredientName.setText(ingredient.getNombre()); // Mostrar el nombre
        holder.ingredientAmount.setText(String.valueOf(ingredient.getCantidad())); // Mostrar la cantidad
    }

    @Override
    public int getItemCount() {
        return ingredientList.size(); // Número total de elementos en la lista
    }

    // Clase interna que representa el ViewHolder de cada elemento
    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredientName;
        public TextView ingredientAmount; // Añadido para mostrar la cantidad

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(android.R.id.text1); // Referencia al TextView para el nombre
            ingredientAmount = itemView.findViewById(android.R.id.text2); // Referencia al TextView para la cantidad
        }
    }
}
