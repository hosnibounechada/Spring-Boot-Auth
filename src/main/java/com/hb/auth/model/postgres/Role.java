package com.hb.auth.model.postgres;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String authority;

    public Role(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return getId() != null && Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
