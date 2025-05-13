package com.example.scheduler.service;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    @Override
    public ScheduleResponseDto create(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule();
        schedule.setTodo(requestDto.getTodo());
        schedule.setWriter(requestDto.getWriter());
        schedule.setPassword(requestDto.getPassword());
        schedule.setCreated(LocalDateTime.now());
        schedule.setModified(LocalDateTime.now());
        repository.save(schedule);
        return toDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        return toDto(schedule);
    }

    @Override
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다."));
        if (!schedule.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        schedule.setTodo(dto.getTodo());
        schedule.setWriter(dto.getWriter());
        schedule.setModified(LocalDateTime.now());
        repository.update(schedule);
        return toDto(schedule);
    }

    @Override
    public void delete(Long id, String password) {
        String savedPassword = repository.findPasswordById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));
        if (!savedPassword.equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        repository.delete(id);
    }

    private ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTodo(),
                schedule.getWriter(),
                schedule.getCreated(),
                schedule.getModified()
        );
    }
}
