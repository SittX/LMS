package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseClassRequest;
import org.kst.lms.models.CourseClass;
import org.kst.lms.models.Subject;
import org.kst.lms.models.User;
import org.kst.lms.services.CourseClassService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class CourseClassController {

    private final CourseClassService courseClassService;

    @PostMapping
    public ResponseEntity<CourseClass> createNewCourseClass(@RequestBody final CourseClassRequest courseClassRequest){
        CourseClass courseClass = this.courseClassService.save(courseClassRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseClass);
    }

    @GetMapping
    public ResponseEntity<List<CourseClass>> getAllClasses(){
        List<CourseClass> classes = this.courseClassService.findAll();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("paging")
    public Page<CourseClass> getAllCourses(@RequestParam(name = "page", defaultValue = "0") final int page,
                                           @RequestParam(name = "size", defaultValue = "10") final int size,
                                           @RequestParam(name = "sortBy", defaultValue = "id") final String sortBy,
                                           @RequestParam(name = "direction", defaultValue = "asc") final String direction) {
        return this.courseClassService.findAll(page, size, sortBy, direction);
    }

    @PostMapping
    public ResponseEntity<CourseClass> createCourse(@RequestBody final CourseClassRequest courseClassReq) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.courseClassService.save(courseClassReq));
    }

    @GetMapping
    public ResponseEntity<List<CourseClass>> getCourses(){
        return ResponseEntity.ok(this.courseClassService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseClass> updateCourse(@PathVariable final long id, @RequestBody CourseClass aCourseClass){
        return ResponseEntity.ok(this.courseClassService.update(id, aCourseClass));
    }

    @GetMapping("/{courseId}/users")
    public ResponseEntity<List<User>> getUsersForCourse(@PathVariable final long courseId){
        List<User> courseUsers = this.courseClassService.getCourseUser(courseId);
        return ResponseEntity.ok(courseUsers);
    }
}
