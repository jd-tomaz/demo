package com.example.demo.rest;

import com.example.demo.services.ScheduleService;
import com.example.demo.to.ScheduleDto;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/schedules")
public class ScheduleResource {

    private final ScheduleService scheduleService;

    public ScheduleResource(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PutMapping
    public ScheduleDto createSchedule(@RequestBody ScheduleDto schedule) {
        return scheduleService.createSchedule(schedule);
    }

    @GetMapping
    public List<ScheduleDto> getSchedules(
            @RequestParam Instant start,
            @RequestParam Instant end
    ) {
        return scheduleService.getSchedules(start, end);
    }

    @DeleteMapping("{scheduleId}")
    public void deleteSchedule(@PathVariable String scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }
}
