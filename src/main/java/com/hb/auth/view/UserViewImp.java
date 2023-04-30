package com.hb.auth.view;

import static com.hb.auth.util.StringUtils.*;

public class UserViewImp implements UserView{
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Integer age;

    public UserViewImp(Long id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return upperCaseFirstLetter(firstName);
    }

    @Override
    public String getLastName() {
        return upperCaseFirstLetter(lastName);
    }

    @Override
    public Integer getAge() {
        return age;
    }

}
