package com.example.scheduler.dto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String writer;
    private LocalDateTime created;
    private LocalDateTime modified;
}
