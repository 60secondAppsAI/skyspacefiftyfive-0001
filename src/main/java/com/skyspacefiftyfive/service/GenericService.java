package com.skyspacefiftyfive.service;

import com.skyspacefiftyfive.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}