package com.hb.auth.service;

import com.hb.auth.model.postgres.Device;
import com.hb.auth.model.postgres.User;
import com.hb.auth.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public Device create(User user, String refreshToken, LocalDateTime expiresAt, String deviceName) {
        Device device = Device.builder().user(user).refreshToken(refreshToken).expiresAt(expiresAt).deviceName(deviceName).build();
        return deviceRepository.save(device);
    }

    public void deleteByUserIdAndRefreshToken(Long userId, String token) {
        deviceRepository.deleteAllByUserIdAndRefreshToken(userId, token);
    }
}
