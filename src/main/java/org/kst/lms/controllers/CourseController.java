package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.Course;
import org.kst.lms.models.User;
import org.kst.lms.services.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody final Course course) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.courseService.save(course));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(){
        return ResponseEntity.ok(this.courseService.findAll());
    }

    @GetMapping("paging")
    public Page<Course> getAllCourses(@RequestParam(name = "page", defaultValue = "0") final int page,
                                      @RequestParam(name = "size", defaultValue = "10") final int size,
                                      @RequestParam(name = "sortBy", defaultValue = "id") final String sortBy,
                                      @RequestParam(name = "direction", defaultValue = "asc") final String direction) {
        return this.courseService.findAll(page, size, sortBy, direction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable final long id, @RequestBody Course course){
           return ResponseEntity.ok(this.courseService.update(id, course));
    }

    @GetMapping("/{courseId}/users")
    public ResponseEntity<List<User>> getUsersForCourse(@PathVariable final long courseId){
        List<User> courseUsers = this.courseService.getCourseUser(courseId);
        return ResponseEntity.ok(courseUsers);
    }
}
