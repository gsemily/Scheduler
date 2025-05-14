package com.example.scheduler.dto;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

//응답 DTO
@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String writer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modified;
}
