package com.rootimpact.anjeonhaejo.service;


import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.rootimpact.anjeonhaejo.domain.AudioAnalysis;
import com.rootimpact.anjeonhaejo.repository.AudioAnalysisRepository;
import com.rootimpact.anjeonhaejo.responseDTO.EmergencyDecibelResponseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.springframework.stereotype.Service;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.apache.hc.core5.http.io.entity.EntityUtils; // 추가

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AudioService {

    private final AudioAnalysisRepository audioAnalysisRepository;
    private static final String DJANGO_API_URL = "http://localhost:8000/api/upload/";

    public EmergencyDecibelResponseDTO analyzeAudio(MultipartFile file, double decibel, String workerZone) throws Exception {
        // 파일을 임시 저장
        Path tempFile = Files.createTempFile("audio_", file.getOriginalFilename());
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        // Django 서버로 파일 전송 및 응답 수신
        JSONObject responseJson = sendToDjangoServer(tempFile.toFile());

        // JSON 데이터를 Java 객체로 변환 (Jackson 사용)
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory(); // TypeFactory를 사용하여 List<String> 타입 지정
        List<String> detectedKeywords = objectMapper.readValue(
                responseJson.getJSONArray("detected_keywords").toString(),
                typeFactory.constructCollectionType(List.class, String.class) // List<String> 타입으로 지정
        );

        // 빈 배열일 경우 안전하게 처리
        if (detectedKeywords == null) {
            detectedKeywords = new ArrayList<>();
        }

        // 결과 저장
        AudioAnalysis audioAnalysis = AudioAnalysis.builder()
                .filename(file.getOriginalFilename())
                .soundClass(responseJson.getString("sound_class"))
                .transcription(responseJson.getString("transcription"))
                .detectedKeywords(detectedKeywords) // JSON 배열을 Java 리스트로 변환해서 저장
                .decibel(decibel)
                .workerZone(workerZone)
                .build();

        audioAnalysisRepository.save(audioAnalysis);

        EmergencyDecibelResponseDTO responseDTO = new EmergencyDecibelResponseDTO(
                audioAnalysis.getCreateTime(),
                audioAnalysis.getWorkerZone(),
                audioAnalysis.getDecibel(),
                audioAnalysis.getSoundClass(),
                audioAnalysis.getTranscription());

        return responseDTO;
    }

    private JSONObject sendToDjangoServer(File file) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadRequest = new HttpPost(DJANGO_API_URL);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file);
            uploadRequest.setEntity(builder.build());

            try (CloseableHttpResponse response = httpClient.execute(uploadRequest)) {
                HttpEntity entity = response.getEntity();
                String jsonResponse = EntityUtils.toString(entity); // 수정된 부분

                System.out.println("💡 Django 응답: " + jsonResponse); // 로그 추가

                return new JSONObject(jsonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();  // 오류 로그 출력
            throw new RuntimeException("Django 서버 통신 중 오류 발생: " + e.getMessage());
        }
    }



    public List<AudioAnalysis> getAllAudioFiles() {
        return audioAnalysisRepository.findAll();
    }

}
