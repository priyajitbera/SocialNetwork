package com.priyajit.project.socialnetwork.service;

import java.util.List;

public interface CommonService <T, ID>{

    T getById(ID id);

    List<T> getAllByIds(List<ID> ids);

    T create(T t);
}
