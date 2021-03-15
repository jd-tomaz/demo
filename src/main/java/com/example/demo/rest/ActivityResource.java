package com.example.demo.rest;

import com.example.demo.services.ActivityService;
import com.example.demo.to.ActivityDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/activities")
public class ActivityResource {

    private final ActivityService activityService;

    public ActivityResource(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PutMapping
    public ActivityDto createActivity(@RequestBody ActivityDto activity) {
        return activityService.createOrUpdateActivity(activity);
    }

    @GetMapping
    public List<ActivityDto> getAllActivities(@RequestParam(required = false) List<String> tags) {
        if (tags == null) {
            return activityService.getActivities();
        }
        return activityService.getActivities(tags);
    }

    @GetMapping("{activityId}")
    public ActivityDto getActivity(@PathVariable String activityId) {
        return activityService.getActivity(activityId);
    }

    @DeleteMapping("{activityId}")
    public void deleteActivity(@PathVariable String activityId) {
        activityService.deleteActivity(activityId);
    }

}
