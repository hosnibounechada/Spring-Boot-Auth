package com.hb.auth.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
        // TODO Auto-generated method stub
        return this.authority;
    }
}