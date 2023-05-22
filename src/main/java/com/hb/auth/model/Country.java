package com.hb.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*public record Country(
        @Id
        Integer id,
        String name,
        String emoji,
        String currency,
        String code,
        String capital
) {
}*/
@Entity(name = "Country")
@Table(
        name = "countries"
)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Integer id;
        String name;
        String emoji;
        String currency;
        String code;
        String capital;
}
