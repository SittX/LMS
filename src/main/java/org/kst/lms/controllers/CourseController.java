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
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
       return new ResponseEntity<>(this.courseService.save(course), HttpStatus.CREATED);
    }

    @GetMapping
    public Page<Course> getAllCourses(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return this.courseService.findAll(page, size);
    }
}
