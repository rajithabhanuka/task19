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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping(value = "/test")
    public ResponseEntity<Object> testAsynch() throws InterruptedException, ExecutionException, JsonProcessingException {
        CompletableFuture<Object> c1Ouput = asyncService.getC1Ouput();
        CompletableFuture<Object> c2Ouput = asyncService.getC2Ouput();

        CompletableFuture.allOf(c1Ouput, c2Ouput).join();

        Map c1 = (HashMap) c1Ouput.get();
        Map c2 = (HashMap) c2Ouput.get();

//        ObjectMapper mapper = new ObjectMapper();

//        JsonNode actualObj1 = mapper.readTree(c1.toString());
//        JsonNode actualObj2 = mapper.readTree(c2.toString());

//        Stream combined = Stream.concat(c1.entrySet().stream(), c2.entrySet().stream());
//
//        Map<String, String> result = combined.collect(
//                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        Map<String, Object> map = new HashMap<>();
//        map.put("actions",actualObj1);
//        map.put("operations",actualObj2);

        c1.putAll(c2);

        return ResponseEntity.status(HttpStatus.OK).body(c1);

    }
}
