package com.ebac.practica60HibernateJPA.Model;

import com.ebac.practica60HibernateJPA.DTO.OperacionesCrud;
import com.ebac.practica60HibernateJPA.DTO.Telefono;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class TelefonoModel implements OperacionesCrud<Telefono> {
    private final EntityManager entityManager;

    public TelefonoModel (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Telefono> save(Telefono telefono) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(telefono);
            transaction.commit();
            System.out.println("Telefono Guardado: " + Optional.of(telefono));
            return Optional.of(telefono);
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al ejecutar el INSERT");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Telefono> getID(int id) {
        Telefono telefonoEncontrado = entityManager.find(Telefono.class, id);
        System.out.println("Registro econtrado : " + telefonoEncontrado);
        return Optional.of(telefonoEncontrado);
    }

    @Override
    public Optional<List<Telefono>> getAll() {
        try {
            String sqlSelect = "SELECT * FROM telefonos";
            List<Telefono> registros = entityManager.createNativeQuery(sqlSelect, Telefono.class).getResultList();
            for(Telefono telefono : registros){
                System.out.println(telefono);
            }
            return Optional.of(registros);
        } catch (Exception e) {
            System.out.println("Error al ejecutar el SELECT : " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Telefono> update(Telefono telefono) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(telefono);
            transaction.commit();
            System.out.println("Exito en actualizar el registro : " + telefono);
            return Optional.of(telefono);
        } catch (Exception e){
            transaction.rollback();
            System.out.println("Error al ejecutar el UPDATE : " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Telefono> delete(Telefono telefono) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(telefono);
            transaction.commit();
            System.out.println("Eliminacion Satisfactoria de : " + telefono);
            return Optional.of(telefono);
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al ejecutar el DELETE : " + e.getMessage());
            return Optional.empty();
        }

    }
}
