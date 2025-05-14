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

//Service 구현 클래스
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository repository;

    //ScheduleRepository 의존성 처리
    @Autowired
    public ScheduleServiceImpl(ScheduleRepository repository) {
        this.repository = repository;
    }

    //일정 생성
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

    //전테 일정 조회
    @Override
    public List<ScheduleResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    //선택 일정 조회
    @Override
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        return toDto(schedule);
    }

    //일정 수정
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

    //일정 삭제
    @Override
    public void delete(Long id, String password) {
        String savedPassword = repository.findPasswordById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));
        if (!savedPassword.equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        repository.delete(id);
    }

    //Schedule Entity → DTO 변환
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
