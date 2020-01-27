package com.zoomcare.candidatechallenge.dao;

import java.util.List;

/**
 * Dao is a simple interface for any data abstraction object
 * The generic param M lets you use whatever model you like
 */
public interface Dao<M> {
    M get(long id);

    List<M> getAll();

    M save(M model);

    int update(M model);

    void delete(M model);
}