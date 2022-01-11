package com.empresadecupones.app.modelo.entity;


import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table
public class Cupon implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "restaurante")
    private String restaurante;
    
    @Column(name = "rfc_restaurante")
    private String rfcRestaurante;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "fecha_caducidad")
    private String fecha_caducidad;
    
    @Column(name = "utilizado")
    private Boolean utilizado;
    
    @Column(name = "caducado")
    private Boolean caducado;
    
    private static final long serialVersionUID = 1L;
    
    public Cupon() {
    }
    
    public Cupon(final int id) {
        this.id = id;
    }
    
    public Cupon(final int id, final String restaurante, final String rfcRestaurante, final String descripcion, final String fecha_caducidad, final Boolean utilizado, final Boolean caducado) {
        this.id = id;
        this.restaurante = restaurante;
        this.rfcRestaurante = rfcRestaurante;
        this.descripcion = descripcion;
        this.fecha_caducidad = fecha_caducidad;
        this.utilizado = utilizado;
        this.caducado = caducado;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getRestaurante() {
        return this.restaurante;
    }
    
    public void setRestaurante(final String restaurante) {
        this.restaurante = restaurante;
    }
    
    public String getRfcRestaurante() {
        return this.rfcRestaurante;
    }
    
    public void setRfcRestaurante(final String rfcRestaurante) {
        this.rfcRestaurante = rfcRestaurante;
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getFecha_caducidad() {
        return this.fecha_caducidad;
    }
    
    public void setFecha_caducidad(final String fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }
    
    public Boolean getUtilizado() {
        return this.utilizado;
    }
    
    public void setUtilizado(final Boolean utilizado) {
        this.utilizado = utilizado;
    }
    
    public Boolean getCaducado() {
        return this.caducado;
    }
    
    public void setCaducado(final Boolean caducado) {
        this.caducado = caducado;
    }
    
    public static long getSerialversionuid() {
        return 1L;
    }
}