package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseClassRequest;
import org.kst.lms.mappers.CourseClassMapper;
import org.kst.lms.models.CourseClass;
import org.kst.lms.services.CourseClassService;
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

    @PutMapping("/{classId}")
    public ResponseEntity<CourseClass> updateClass(@PathVariable final long classId, @RequestBody final CourseClassRequest courseClassRequest){
       CourseClass updatedClass = this.courseClassService.update(classId, courseClassRequest);
       return ResponseEntity.ok(updatedClass);
    }
}
