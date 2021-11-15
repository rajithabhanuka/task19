package com.code.task19.dto;

import lombok.Data;

import java.util.List;

@Data
public class AsyncDto {

    private List<ActionDto> actions;

    private List<OperationDto> operations;

}
