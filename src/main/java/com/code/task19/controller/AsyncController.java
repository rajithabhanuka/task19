package com.code.task19.controller;

import com.code.task19.dto.AsyncDto;
import com.code.task19.dto.C1OutDto;
import com.code.task19.dto.C2OutDto;
import com.code.task19.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping(value = "/test")
    public ResponseEntity<AsyncDto> testAsynch() throws InterruptedException, ExecutionException {
        CompletableFuture<C1OutDto> c1Ouput = asyncService.getC1Ouput();
        CompletableFuture<C2OutDto> c2Ouput = asyncService.getC2Ouput();

        CompletableFuture.allOf(c1Ouput, c2Ouput).join();

        C1OutDto c1 = c1Ouput.get();
        C2OutDto c2 = c2Ouput.get();

        AsyncDto asyncDto = new AsyncDto();
        asyncDto.setActions(c1.getActions());
        asyncDto.setOperations(c2.getOperations());

        return ResponseEntity.status(HttpStatus.OK).body(asyncDto);
    }
}
