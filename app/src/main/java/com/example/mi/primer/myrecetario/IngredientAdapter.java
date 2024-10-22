package com.example.mi.primer.myrecetario;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private static final String TAG = "IngredientAdapter"; // Tag para Logcat
    private List<Ingredientes.Ingrediente> ingredientList; // Lista de ingredientes

    // Constructor para pasar la lista de ingredientes al adaptador
    public IngredientAdapter(List<Ingredientes.Ingrediente> ingredientList) {
        this.ingredientList = ingredientList;
        Log.d(TAG, "IngredientAdapter inicializado con " + ingredientList.size() + " ingredientes");
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout para cada elemento del RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        Log.d(TAG, "onCreateViewHolder: Inflando nuevo ViewHolder para ingrediente");
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        // Asignar la información del ingrediente a cada elemento
        Ingredientes.Ingrediente ingredient = ingredientList.get(position);
        Log.d(TAG, "onBindViewHolder: Posición " + position + " - Ingrediente: " + ingredient.getNombre() + ", Cantidad: " + ingredient.getCantidad());

        holder.ingredientName.setText(ingredient.getNombre()); // Mostrar el nombre

        // Formatear la cantidad a dos decimales para mayor claridad
        String cantidadFormateada = String.format("%.2f", ingredient.getCantidad());
        holder.ingredientAmount.setText(cantidadFormateada); // Mostrar la cantidad
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: Número de ingredientes: " + ingredientList.size());
        return ingredientList.size(); // Número total de elementos en la lista
    }

    // Clase interna que representa el ViewHolder de cada elemento
    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredientName;
        public TextView ingredientAmount; // TextView para mostrar la cantidad

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(android.R.id.text1); // Referencia al TextView para el nombre
            ingredientAmount = itemView.findViewById(android.R.id.text2); // Referencia al TextView para la cantidad
            Log.d(TAG, "IngredientViewHolder: ViewHolder creado");
        }
    }

    // Método para actualizar la lista de ingredientes
    public void setIngredients(List<Ingredientes.Ingrediente> newIngredientList) {
        this.ingredientList = newIngredientList;
        Log.d(TAG, "setIngredients: Lista de ingredientes actualizada con " + newIngredientList.size() + " ingredientes");
        notifyDataSetChanged();
    }
}
