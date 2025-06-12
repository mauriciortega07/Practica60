package com.ebac.practica60HibernateJPA.Model;

import com.ebac.practica60HibernateJPA.DTO.OperacionesCrud;
import com.ebac.practica60HibernateJPA.DTO.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class UsuarioModel implements OperacionesCrud<Usuario> {
    private final EntityManager entityManager;

    public UsuarioModel(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //METODO PARA GUARDAR REGISTROS
    @Override
    public Optional<Usuario> save(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(usuario);
            System.out.println("Usuario Guardado: " + Optional.of(usuario));
            transaction.commit();
            return Optional.of(usuario);
        } catch (Exception e){
            transaction.rollback();
            System.out.println("Error al ejecutar el INSERT:" + e.getMessage());
        }
        return Optional.empty();
    }

    //METODO PARA OBTENER UN REGISTRO
    @Override
    public Optional<Usuario> getID(int id) {
        Usuario usuarioEncontrado = entityManager.find(Usuario.class, id);
        System.out.println("Usuario encontrado : " + usuarioEncontrado);
        return Optional.of(usuarioEncontrado);
    }

    //METODO PARA OBTENER REGISTROS
    @Override
    public Optional<List<Usuario>> getAll() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
            Root<Usuario> rootEntry = criteriaQuery.from(Usuario.class);
            CriteriaQuery<Usuario> select = criteriaQuery.select(rootEntry);

            TypedQuery<Usuario> querySelect = entityManager.createQuery(select);
            List<Usuario> registros = querySelect.getResultList();
            for(Usuario usuario : registros){
                System.out.println(usuario);
            }

            return Optional.of(registros);
        } catch (Exception e) {
            System.out.println("Error al ejecutar el SELECT : " + e.getMessage());
        }

        return Optional.empty();
    }

    //METODO PARA ACTUALIZAR REGISTROS
    @Override
    public Optional<Usuario> update(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(usuario);
            transaction.commit();
            System.out.println("Exito en actualizar el registro : " + usuario);
            return Optional.of(usuario);
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al ejecutar el UPDATE : " + e.getMessage());
        }
        return Optional.empty();
    }

    //METODO PARA ELIMINAR REGISTROS
    @Override
    public Optional<Usuario> delete(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(usuario);
            transaction.commit();
            System.out.println("Eliminacion Satisfactoria de : " + usuario);
            return Optional.of(usuario);
        } catch (Exception e){
            transaction.rollback();
            System.out.println();
        }
        return Optional.empty();
    }



}
