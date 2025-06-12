package com.ebac.practica60HibernateJPA.Model;

import com.ebac.practica60HibernateJPA.DTO.Direccion;
import com.ebac.practica60HibernateJPA.DTO.OperacionesCrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class DireccionModel implements OperacionesCrud<Direccion> {
    private final EntityManager entityManager;

    public DireccionModel(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public Optional<Direccion> save(Direccion direccion) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(direccion);
            transaction.commit();
            System.out.println("Direccion Guardada: " + Optional.of(direccion));
            return Optional.of(direccion);
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al ejecutar el INSERT : " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Direccion> getID(int id) {
        Direccion direccionEncontrada = entityManager.find(Direccion.class, id);
        System.out.println("Registro encontrado : " + direccionEncontrada);
        return Optional.of(direccionEncontrada);
    }

    @Override
    public Optional<List<Direccion>> getAll() {
        try {
            String sqlSelect = "SELECT * FROM direcciones";
            List<Direccion> resultados = entityManager.createNativeQuery(sqlSelect).getResultList();
            for(Direccion direccion : resultados){
                System.out.println(direccion);
            }
            return Optional.of(resultados);
        } catch (Exception e) {
            System.out.println("Error al ejecutar el SELECT : " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Direccion> update(Direccion direccion) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(direccion);
            transaction.commit();
            System.out.println("Exito en actualizar el registro : " + direccion);
            return Optional.of(direccion);
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al ejecutar el UPDATE : " + e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<Direccion> delete(Direccion direccion) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(direccion);
            transaction.commit();
            System.out.println("Eliminacion Satisfactoria de : " + direccion);
            return Optional.of(direccion);

        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al ejecutar el DELETE : " + e.getMessage());
            return Optional.empty();
        }

    }
}
