package com.henzel.repository.intf;

import java.util.List;

public interface RepositoryIntf<T> {

    T save(T t);
    void remove(T t);
    T getById(long id);
    List<?> getByQuery(String query);
    T getObjectByQuery(String query);
}