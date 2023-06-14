package com.hb.auth.model.postgres;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity(name = "Country")
@Table(
        name = "countries"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
                Country country = (Country) o;
                return getId() != null && Objects.equals(getId(), country.getId());
        }

        @Override
        public int hashCode() {
                return getClass().hashCode();
        }
}
