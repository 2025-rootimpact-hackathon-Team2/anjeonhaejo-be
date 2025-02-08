package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.domain.User;
import com.rootimpact.anjeonhaejo.domain.WorkerLine;
import com.rootimpact.anjeonhaejo.repository.UserRepository;
import com.rootimpact.anjeonhaejo.repository.WorkerLineRepository;
import com.rootimpact.anjeonhaejo.requestDTO.LoginDTO;
import com.rootimpact.anjeonhaejo.requestDTO.RequestRegisterDTO;
import com.rootimpact.anjeonhaejo.security.custom.CustomUserInfoDto;
import com.rootimpact.anjeonhaejo.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WorkerLineRepository workerLineRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Transactional
    public void register(RequestRegisterDTO dto){

        WorkerLine workerLine = workerLineRepository.findById(dto.getWorkerLineId()).orElse(null);

        User user = new User(
                dto.getUsername(),
                dto.getUserState(),
                encoder.encode(dto.getPassword()),
                dto.getEmail(),
                dto.getRole(),
                dto.getTaskManager(),
                workerLine
                );
        userRepository.save(user);

    }


    public String login(LoginDTO dto) {
        System.out.println("service "+dto.getEmail() + " " + dto.getPassword());
        User user = userRepository.findUserByEmail(dto.getEmail());

        if(user == null){
            return "you can't use we service";
        }

        if(!encoder.matches(dto.getPassword(), user.getPassword())) {
            return "password error";
        }

        CustomUserInfoDto customUserInfoDto = new CustomUserInfoDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
        return jwtUtil.createAccessToken(customUserInfoDto);

    }



    public ResponseEntity<Boolean> checkUserName(String username) {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }


}