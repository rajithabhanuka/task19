package com.code.task19.dto;

import lombok.Data;
import org.json.JSONObject;

import java.util.List;

@Data
public class AsyncDto {

//    private List<ActionDto> actions;
//
//    private List<OperationDto> operations;

    private JSONObject actions;

    private JSONObject operations;

}
