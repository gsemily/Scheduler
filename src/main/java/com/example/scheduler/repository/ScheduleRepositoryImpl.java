package com.example.scheduler.repository;
import com.example.scheduler.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//Repository 구현 클래스
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    //JdbcTemplate 의존성 처리
    @Autowired
    public ScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //일정 저장
    @Override
    public Schedule save(Schedule schedule) {
        String sql = "INSERT INTO schedule (todo, writer, password, created_date, modified_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                schedule.getTodo(),
                schedule.getWriter(),
                schedule.getPassword(),
                schedule.getCreated(),
                schedule.getModified());
        return schedule;
    }

    //전체 일정 반환
    @Override
    public List<Schedule> findAll() {
        String sql = "SELECT * FROM schedule ORDER BY modified_date DESC";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    //선택 일정 반환
    @Override
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        List<Schedule> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    //비밀번호 조회
    @Override
    public Optional<String> findPasswordById(Long id) {
        String sql = "SELECT password FROM schedule WHERE id = ?";
        List<String> result = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("password"), id);
        return result.stream().findFirst();
    }

    //일정 수정
    @Override
    public void update(Schedule schedule) {
        String sql = "UPDATE schedule SET todo = ?, writer = ?, modified_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                schedule.getTodo(),
                schedule.getWriter(),
                schedule.getModified(),
                schedule.getId());
    }

    //일정 삭제
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    //ResultSet -> Schedule 객체 매핑
    private Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Schedule(
                rs.getLong("id"),
                rs.getString("todo"),
                rs.getString("writer"),
                rs.getString("password"),
                rs.getTimestamp("created_date").toLocalDateTime(),
                rs.getTimestamp("modified_date").toLocalDateTime()
        );
    }
}
