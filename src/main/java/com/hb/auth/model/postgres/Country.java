package com.hb.auth.model.postgres;

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
        @SequenceGenerator(
                name = "country_sequence",
                sequenceName = "country_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "country_sequence"
        )
        Long id;
        String name;
        String emoji;
        String currency;
        String code;
        String capital;
}
