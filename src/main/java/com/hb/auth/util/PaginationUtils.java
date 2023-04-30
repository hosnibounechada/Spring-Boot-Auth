package com.hb.auth.util;

import com.hb.auth.mapper.GenericMapper;
import com.hb.auth.payload.response.PageResponse;
import org.springframework.data.domain.Page;

public class PaginationUtils {
    public static  <ENTITY, REQUEST, RESPONSE> PageResponse<RESPONSE> generatePageableResponse(Page<ENTITY> page, GenericMapper<ENTITY, REQUEST, RESPONSE> mapper){

        return new PageResponse<>(page.getNumber(),page.getNumberOfElements(), page.getTotalPages(), (int) page.getTotalElements(), mapper.entitiesToResponses(page.getContent()));
    }
    public static  <VIEW> PageResponse<VIEW> generatePageableResponse(Page<VIEW> page){

        return new PageResponse<>(page.getNumber(),page.getNumberOfElements(), page.getTotalPages(), (int) page.getTotalElements(), page.getContent());
    }
}
