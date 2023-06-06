package com.hb.auth.repository;

import com.hb.auth.model.postgres.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    void deleteAllByUserIdAndRefreshToken(Long userId, String refreshToken);
    Device findDeviceByUserIdAndRefreshToken(Long userId, String refreshToken);
}
