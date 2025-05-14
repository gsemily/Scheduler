package com.example.scheduler.service;
import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import java.util.List;

//Service 인터페이스
public interface ScheduleService {
    ScheduleResponseDto create(ScheduleRequestDto requestDto);
    List<ScheduleResponseDto> findAll();
    ScheduleResponseDto findById(Long id);
    ScheduleResponseDto update(Long id, ScheduleRequestDto dto);
    void delete(Long id, String password);
}
