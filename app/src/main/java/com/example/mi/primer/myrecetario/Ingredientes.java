package com.example.mi.primer.myrecetario;

    public class Ingredientes {

        public static class Ingrediente {
            private String nombre;
            private double cantidad;

            // Constructor vacío para Firebase
            public Ingrediente() {
            }

            // Constructor con parámetros
            public Ingrediente(String nombre, double cantidad) {
                this.nombre = nombre;
                this.cantidad = cantidad;
            }

            // Getters y setters
            public String getNombre() {
                return nombre;
            }

            public void setNombre(String nombre) {
                this.nombre = nombre;
            }

            public double getCantidad() {
                return cantidad;
            }

            public void setCantidad(double cantidad) {
                this.cantidad = cantidad;
            }
        }
    }
