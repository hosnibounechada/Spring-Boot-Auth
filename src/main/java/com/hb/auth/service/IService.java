package com.hb.auth.service;

import com.hb.auth.payload.response.PageResponse;

public interface IService<ID, CREATE, UPDATE, RESPONSE> {
    PageResponse<RESPONSE> getAll();
    PageResponse<RESPONSE> getByPages(int pageNumber, int pageSize, String sortBy, String direction);
    RESPONSE getById(ID id);
    RESPONSE create(CREATE request);
    RESPONSE update(ID id, UPDATE request);
    void deleteById(ID id);
    void delete(ID id);
}
