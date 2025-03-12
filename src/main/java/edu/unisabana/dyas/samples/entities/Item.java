/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unisabana.dyas.samples.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author cesarvefe
 */
public class Item implements Serializable {
    private TipoItem tipo;
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechalanzamiento;
    private long tarifaxdia; 
    private String formatorenta;
    private String genero;

    public Item(TipoItem tipo, int id, String nombre, String descripcion, Date fechalanzamiento, long tarifaxdia, String formatorenta, String genero) {
        this.tipo = tipo;
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechalanzamiento = fechalanzamiento;
        this.tarifaxdia = tarifaxdia;
        this.formatorenta = formatorenta;
        this.genero = genero;
    }

    public Item() {
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {  
        this.descripcion = descripcion;
    }

    public Date getFechalanzamiento() {
        return fechalanzamiento;
    }

    public void setFechalanzamiento(Date fechalanzamiento) {
        this.fechalanzamiento = fechalanzamiento;
    }

    public long getTarifaxdia() {
        return tarifaxdia;
    }

    public void setTarifaxdia(long tarifaxdia) { 
        this.tarifaxdia = tarifaxdia;
    }

    public String getFormatorenta() {
        return formatorenta;
    }

    public void setFormatorenta(String formatorenta) {
        this.formatorenta = formatorenta;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Item{" + "tipo=" + tipo + ", id=" + id + ", nombre=" + nombre + "tipo=" + tipo + ", tarifaxdia=" + tarifaxdia + ", formatorenta=" + formatorenta +  ", genero=" + genero + '}';
    }
}
