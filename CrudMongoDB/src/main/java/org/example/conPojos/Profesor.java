package org.example.conPojos;

import java.util.Arrays;

public class Profesor {
    public String nombre;
    public String apellido;
    public String[] especialidad;
    public boolean esTitular;
    public boolean esAsociado;
    public String vehiculo;

    public Profesor(String nombre, String apellido, String[] especialidad, boolean esTitular, boolean esAsociado, String vehiculo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.esTitular = esTitular;
        this.esAsociado = esAsociado;
        this.vehiculo = vehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String[] getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String[] especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isEsTitular() {
        return esTitular;
    }

    public void setEsTitular(boolean esTitular) {
        this.esTitular = esTitular;
    }

    public boolean isEsAsociado() {
        return esAsociado;
    }

    public void setEsAsociado(boolean esAsociado) {
        this.esAsociado = esAsociado;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", especialidad=" + Arrays.toString(especialidad) +
                ", esTitular=" + esTitular +
                ", esAsociado=" + esAsociado +
                ", vehiculo='" + vehiculo + '\'' +
                '}';
    }
}
