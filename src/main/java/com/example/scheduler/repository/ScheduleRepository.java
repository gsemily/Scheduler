package com.example.scheduler.repository;
import com.example.scheduler.entity.Schedule;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    List<Schedule> findAll();
    Optional<Schedule> findById(Long id);
    Optional<String> findPasswordById(Long id);
    void update(Schedule schedule);
    void delete(Long id);
}
