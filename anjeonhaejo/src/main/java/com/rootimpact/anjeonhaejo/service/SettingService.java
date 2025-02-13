package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.domain.User;
import com.rootimpact.anjeonhaejo.global.exception.user.UserNotFoundException;
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
    public ReadUserMyPageResponse showMyPageByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("그런 유저 없어 !!"));

        return ReadUserMyPageResponse.from(user);
    }

    @Transactional
    public Long modifyUserMyPage(Long id, UpdateUserMyPageRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow();

        user.modifyUser(
                request.name() != null ? request.name() : user.getUsername(),
                request.role() != null ? request.role() : user.getRole(),
                request.factory() != null ? request.factory() : user.getFactory(),
                request.department() != null ? request.department() : user.getDepartment()
        );

        return userRepository.save(user).getId();
    }

}
