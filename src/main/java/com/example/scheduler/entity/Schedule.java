package com.example.scheduler.entity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

//일정 domain entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Long id;   //고유 식별자
    private String todo;   //일정
    private String writer;   //작성자
    private String password;   //비밀번호
    private LocalDateTime created;   //생성일자
    private LocalDateTime modified;   //수정일자
}
