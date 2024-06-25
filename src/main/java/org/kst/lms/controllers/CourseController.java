package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.Course;
import org.kst.lms.services.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody final Course course) {
        return new ResponseEntity<>(this.courseService.save(course), HttpStatus.CREATED);
    }

    @GetMapping
    public Page<Course> getAllCourses(@RequestParam(name = "page", defaultValue = "0") final int page,
                                      @RequestParam(name = "size", defaultValue = "10") final int size,
                                      @RequestParam(name = "sortBy", defaultValue = "id") final String sortBy,
                                      @RequestParam(name = "direction", defaultValue = "asc") final String direction) {
        return this.courseService.findAll(page, size, sortBy, direction);
    }
}
