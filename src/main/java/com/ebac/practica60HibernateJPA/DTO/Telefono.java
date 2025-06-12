package com.ebac.practica60HibernateJPA.DTO;

import jakarta.persistence.*;

@Entity
@Table(name = "telefonos")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTelefono;

    @Column(name = "idUsuario")
    private int idUsuario;

    @Column(name = "numero")
    private String numero;

    @Column(name = "tipoTelefono")
    private String tipoTelefono;

    public int getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(String tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "idTelefono=" + idTelefono +
                ", idUsuario=" + idUsuario +
                ", numero='" + numero + '\'' +
                ", tipo='" + tipoTelefono + '\'' +
                '}';
    }
}


