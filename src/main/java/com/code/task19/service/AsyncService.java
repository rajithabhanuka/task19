package com.code.task19.service;

import com.code.task19.dto.C1OutDto;
import com.code.task19.dto.C2OutDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

    @Async("asyncExecutor")
    public CompletableFuture<C1OutDto> getC1Ouput() throws InterruptedException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        log.info("getC1Ouput starts");

        ResponseEntity<C1OutDto> c1OutDtoResponseEntity = restTemplate.exchange("http://localhost:8080/mock/c1", HttpMethod.GET, entity, C1OutDto.class);

        C1OutDto c1OutDto = null;

        if (c1OutDtoResponseEntity != null) {
            c1OutDto = c1OutDtoResponseEntity.getBody();
        }

        // .(url2,HttpMethod.GET,entity,object.class);

        Thread.sleep(1000L);
        log.info("getC1Ouput completed");

        return CompletableFuture.completedFuture(c1OutDto);
    }

    @Async("asyncExecutor")
    public CompletableFuture<C2OutDto> getC2Ouput() throws InterruptedException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        log.info("getC2Ouput starts");

        ResponseEntity<C2OutDto> c2OutDtoResponseEntity = restTemplate.exchange("http://localhost:8080/mock/c2", HttpMethod.GET, entity, C2OutDto.class);

        C2OutDto c2OutDto = null;

        if (c2OutDtoResponseEntity != null) {
            c2OutDto = c2OutDtoResponseEntity.getBody();
        }

        Thread.sleep(1000L);
        log.info("getC2Ouput completed");

        return CompletableFuture.completedFuture(c2OutDto);
    }
}
