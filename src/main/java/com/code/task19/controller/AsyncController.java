package com.code.task19.controller;

import com.code.task19.dto.AsyncDto;
import com.code.task19.dto.C1OutDto;
import com.code.task19.dto.C2OutDto;
import com.code.task19.service.AsyncService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping(value = "/test")
    public ResponseEntity<Object> testAsynch() throws InterruptedException, ExecutionException, JsonProcessingException {
        CompletableFuture<String> c1Ouput = asyncService.getC1Ouput();
        CompletableFuture<String> c2Ouput = asyncService.getC2Ouput();

        CompletableFuture.allOf(c1Ouput, c2Ouput).join();

        String c1 = c1Ouput.get();
        String c2 = c2Ouput.get();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode actualObj1 = mapper.readTree(c1);
        JsonNode actualObj2 = mapper.readTree(c2);

        JSONObject jsonObjectC1 = new JSONObject(c1);
        JSONObject jsonObjectC2 = new JSONObject(c2);


        Map<String, Object> map = new HashMap<>();
        map.put("actions",actualObj1);
        map.put("operations",actualObj2);

        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
