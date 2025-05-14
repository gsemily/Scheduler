package com.example.scheduler.controller;
import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//Controller 클래스
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService service;

    //ScheduleService 의존성 처리
    @Autowired
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    //일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> create(@RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    //전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    //선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    //일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String password = body.get("password");
        service.delete(id, password);
        return ResponseEntity.ok("일정이 삭제되었습니다.");
    }
}
