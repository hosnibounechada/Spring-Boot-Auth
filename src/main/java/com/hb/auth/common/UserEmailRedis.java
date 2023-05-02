package com.hb.auth.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@AllArgsConstructor
@Data
public class UserEmailRedis {
    private String code;
    private int counter;

}
