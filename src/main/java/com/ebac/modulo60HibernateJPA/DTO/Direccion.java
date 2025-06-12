package com.ebac.modulo60HibernateJPA.DTO;

import jakarta.persistence.*;

@Entity
@Table(name = "direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDireccion;

    @Column(name = "idUsuario")
    private int idUsuario;

    @Column(name = "estado")
    private String estado;

    @Column(name = "numero")
    private int numero;

    @Column(name = "calle")
    private String calle;

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "idDireccion=" + idDireccion +
                ", idUsuario='" + idUsuario + '\'' +
                ", estado='" + estado + '\'' +
                ", numero=" + numero +
                ", calle='" + calle + '\'' +
                '}';
    }
}
