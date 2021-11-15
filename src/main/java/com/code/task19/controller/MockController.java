package com.code.task19.controller;

import com.code.task19.dto.ActionDto;
import com.code.task19.dto.C1OutDto;
import com.code.task19.dto.C2OutDto;
import com.code.task19.dto.OperationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "mock")
public class MockController {

    @GetMapping(value = "c1")
    public ResponseEntity<C1OutDto> getC1Output(){

        List<ActionDto> actionDtos = new ArrayList<>();
        ActionDto actionDto1 = new ActionDto();
        actionDto1.setId(0);
        actionDto1.setEcp("String");
        actionDto1.setStatus("y");
        actionDto1.setGroupid("String");
        actionDtos.add(actionDto1);

        C1OutDto c1OutDto = new C1OutDto();
        c1OutDto.setActions(actionDtos);

        return ResponseEntity.status(HttpStatus.OK).body(c1OutDto);

    }

    @GetMapping(value = "c2")
    public ResponseEntity<C2OutDto> getC2Output(){

        List<OperationDto> operationDtos = new ArrayList<>();
        OperationDto operationDto1 = new OperationDto();
        operationDto1.setId(0);
        operationDto1.setVcdp("String");
        operationDto1.setStatus("y");
        operationDto1.setGroupid("String");
        operationDtos.add(operationDto1);

        C2OutDto c2OutDto = new C2OutDto();
        c2OutDto.setOperations(operationDtos);

        return ResponseEntity.status(HttpStatus.OK).body(c2OutDto);

    }
}
