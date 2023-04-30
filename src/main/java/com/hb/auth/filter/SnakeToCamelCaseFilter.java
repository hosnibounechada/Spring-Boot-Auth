package com.hb.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.hb.auth.util.StringUtils.*;

//@Component
public class SnakeToCamelCaseFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        Map<String, String[]> paramMap = new HashMap<>(req.getParameterMap());

        Map<String, String[]> convertedParamMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String camelCaseKey = toCamelCase(entry.getKey());
            String[] lowerCaseValues = toLowerCase(entry.getValue());

            convertedParamMap.put(camelCaseKey, lowerCaseValues);
        }
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req) {
            @Override
            public Map<String, String[]> getParameterMap() {
                return convertedParamMap;
            }

            @Override
            public String[] getParameterValues(String name) {
                return convertedParamMap.get(name);
            }

            @Override
            public String getParameter(String name) {
                String[] values = convertedParamMap.get(name);
                if (values != null && values.length > 0) {
                    return values[0];
                } else {
                    return null;
                }
            }
        };

        chain.doFilter(requestWrapper, response);
    }
}
