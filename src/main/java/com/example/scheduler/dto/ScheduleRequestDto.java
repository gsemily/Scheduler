package com.example.scheduler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private String todo;
    private String writer;
    private String password;
}
