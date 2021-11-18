package com.code.task19.controller;

import com.code.task19.service.AsyncService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.CompletableFuture;

public class AsyncControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Autowired
    private AsyncService asyncService;

    @Test
    public void getProductsList() throws Exception {

        CompletableFuture<TestExecutionResult> result = new CompletableFuture<TestExecutionResult>();
        Mockito.doReturn(result).when(asyncService).getC1Ouput();
        Mockito.doReturn(result).when(asyncService).getC2Ouput();

        String uri = "/async/test";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }
}
