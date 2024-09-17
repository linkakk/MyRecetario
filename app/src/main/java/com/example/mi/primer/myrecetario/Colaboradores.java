package com.example.mi.primer.myrecetario;

public class Colaboradores {
    public class User {
        private String nombreColaborador;
        private String lastNameColaborador;
        private String cargo;
        private String edad;
        private String numeroTelefonico;
        private String Contraseña;


        // Constructor vacío requerido por Firebase
        public User() {}

        // Getters y setters

        public User(String nombreColaborador, String lastNameColaborador, String cargo, String edad, String numeroTelefonico, String contraseña) {
            this.nombreColaborador = nombreColaborador;
            this.lastNameColaborador = lastNameColaborador;
            this.cargo = cargo;
            this.edad = edad;
            this.numeroTelefonico = numeroTelefonico;
            Contraseña = contraseña;
        }

        public String getNombreColaborador() {
            return nombreColaborador;
        }

        public void setNombreColaborador(String nombreColaborador) {
            this.nombreColaborador = nombreColaborador;
        }

        public String getLastNameColaborador() {
            return lastNameColaborador;
        }

        public void setLastNameColaborador(String lastNameColaborador) {
            this.lastNameColaborador = lastNameColaborador;
        }

        public String getCargo() {
            return cargo;
        }

        public void setCargo(String cargo) {
            this.cargo = cargo;
        }

        public String getEdad() {
            return edad;
        }

        public void setEdad(String edad) {
            this.edad = edad;
        }

        public String getNumeroTelefonico() {
            return numeroTelefonico;
        }

        public void setNumeroTelefonico(String numeroTelefonico) {
            this.numeroTelefonico = numeroTelefonico;
        }

        public String getContraseña() {
            return Contraseña;
        }

        public void setContraseña(String contraseña) {
            Contraseña = contraseña;
        }
    }

}
