package com.zoomcare.dao;

import java.util.List;

/**
 * Dao is a simple interface for any data abstraction object
 * The generic param M lets you use whatever model you like
 */
public interface Dao<M> {
    M get(long id);

    List<M> getAll();

    void save(M model);

    void update(M model);

    void delete(M model);
}