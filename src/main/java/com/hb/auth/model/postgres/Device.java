package com.hb.auth.model.postgres;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Device")
@Table(name = "devices")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Device {
        @Id
        @SequenceGenerator(
                name = "device_sequence",
                sequenceName = "device_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "device_sequence"
        )
        @Column(
                name = "id"
        )
        private Long id;

        @Column(
                name = "device_name",
                length = 128
        )
        private String deviceName;
        @Column(
                name = "refresh_token",
                columnDefinition = "TEXT"
        )
        private String refreshToken;

        @Column(
                name = "expires_at",
                columnDefinition = "TIMESTAMP"
        )
        private LocalDateTime expiresAt;

        @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "devices_user_id_fkey"))
        @ToString.Exclude
        private User user;
}
