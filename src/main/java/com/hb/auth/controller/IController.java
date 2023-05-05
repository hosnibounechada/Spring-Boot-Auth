package com.hb.auth.controller;

import com.hb.auth.payload.response.PageResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
public interface IController<ID, CREATE, UPDATE, RESPONSE> {
    ResponseEntity<PageResponse<RESPONSE>> getAll();

    ResponseEntity<PageResponse<RESPONSE>> getByPages(int page, int size, String sortBy, String direction);

    ResponseEntity<RESPONSE> getById(ID id);

    ResponseEntity<RESPONSE> create(CREATE request);

    ResponseEntity<RESPONSE> update(ID id, UPDATE request);

    ResponseEntity<Void> delete(ID id);
}
