package com.example.mi.primer.myrecetario;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList; // Asegúrate de tener esta línea de importación

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private static final String TAG = "IngredientAdapter";
    private List<Ingredientes.Ingrediente> ingredientList;

    public IngredientAdapter(List<Ingredientes.Ingrediente> ingredientList) {
        if (ingredientList != null) {
            this.ingredientList = ingredientList;
            Log.d(TAG, "IngredientAdapter inicializado con " + ingredientList.size() + " ingredientes");
        } else {
            Log.w(TAG, "IngredientAdapter inicializado con una lista de ingredientes nula.");
            this.ingredientList = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        Log.d(TAG, "onCreateViewHolder: Inflando nuevo ViewHolder para ingrediente");
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredientes.Ingrediente ingredient = ingredientList.get(position);
        Log.d(TAG, "onBindViewHolder: Posición " + position + " - Ingrediente: " + ingredient.getNombre() + ", Cantidad: " + ingredient.getCantidad());

        holder.ingredientName.setText(ingredient.getNombre());
        String cantidadFormateada = String.format("%.2f", ingredient.getCantidad());
        holder.ingredientAmount.setText(cantidadFormateada);
    }

    @Override
    public int getItemCount() {
        int size = ingredientList != null ? ingredientList.size() : 0;
        Log.d(TAG, "getItemCount: Número de ingredientes: " + size);
        return size;
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredientName;
        public TextView ingredientAmount;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(android.R.id.text1);
            ingredientAmount = itemView.findViewById(android.R.id.text2);
            Log.d(TAG, "IngredientViewHolder: ViewHolder creado");
        }
    }

    public void setIngredients(List<Ingredientes.Ingrediente> newIngredientList) {
        if (newIngredientList != null && !newIngredientList.isEmpty()) {
            this.ingredientList = newIngredientList;
            Log.d(TAG, "setIngredients: Lista de ingredientes actualizada con " + newIngredientList.size() + " ingredientes");
        } else {
            Log.w(TAG, "setIngredients: Se recibió una lista vacía o nula para ingredientes.");
            this.ingredientList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }
}
