package com.example.scheduler.entity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String todo;
    private String writer;
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
}
