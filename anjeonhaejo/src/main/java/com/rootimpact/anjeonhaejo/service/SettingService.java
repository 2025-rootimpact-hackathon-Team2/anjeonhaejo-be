package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.domain.User;
import com.rootimpact.anjeonhaejo.repository.UserRepository;
import com.rootimpact.anjeonhaejo.requestDTO.UpdateUserMyPageRequest;
import com.rootimpact.anjeonhaejo.responseDTO.ReadUserMyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final UserRepository userRepository;

    @Transactional
    public ReadUserMyPageResponse showMyPage(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("그런 유저 없어 !!"));

        return ReadUserMyPageResponse.from(user);
    }

    @Transactional
    public Long modifyUserMyPage(UpdateUserMyPageRequest request) {
        User user = userRepository.findByUsername(request.name());

        user.modifyUser(
                request.name(),
                request.role(),
                request.factory(),
                request.department()
        );

        return userRepository.save(user).getId();
    }
}
