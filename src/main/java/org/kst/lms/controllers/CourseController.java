package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseClassRequest;
import org.kst.lms.models.Course;
import org.kst.lms.models.User;
import org.kst.lms.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @PostMapping
    public ResponseEntity<Course> createNewCourseClass(@RequestBody final CourseClassRequest courseClassRequest){
        logger.info("Creating new course : {}", courseClassRequest.getName());
        Course course = this.courseService.save(courseClassRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllClasses(){
        logger.info("Querying all classes");
        List<Course> classes = this.courseService.findAll();
        return ResponseEntity.ok(classes);
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
        logger.info("Updating course with Id : {}", id);
        return ResponseEntity.ok(this.courseService.update(id, course));
    }

    @GetMapping("/{courseId}/users")
    public ResponseEntity<List<User>> getUsersForCourse(@PathVariable final long courseId){
        List<User> courseUsers = this.courseService.getCourseUser(courseId);
        return ResponseEntity.ok(courseUsers);
    }
}
