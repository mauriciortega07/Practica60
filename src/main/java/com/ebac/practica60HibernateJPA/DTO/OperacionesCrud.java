package com.ebac.practica60HibernateJPA.DTO;

import java.util.List;
import java.util.Optional;

public interface OperacionesCrud<T> {
    Optional<T> save(T t);
    Optional<T> getID(int id);
    Optional<List<T>> getAll();
    Optional<T> update(T t);
    Optional<T> delete(T t);
}
