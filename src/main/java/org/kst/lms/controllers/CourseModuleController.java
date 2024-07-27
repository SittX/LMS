package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.kst.lms.dtos.CourseModuleRequest;
import org.kst.lms.mappers.CourseModuleMapper;
import org.kst.lms.models.CourseModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/v1/modules")
//@RequiredArgsConstructor
//public class CourseModuleController {
//    private final CourseModuleMapper moduleMapper;
//    private final ModuleService moduleService;
//
//    @GetMapping
//    public ResponseEntity<List<CourseModule>> getAllModules(){
//        return ResponseEntity.ok(this.moduleService.findAll());
//    }
//
//    @PostMapping
//    public ResponseEntity<CourseModule> createNewModule(@RequestBody final CourseModuleRequest courseModuleRequest){
//        CourseModule module = this.moduleMapper.toEntity(courseModuleRequest);
//        CourseModule createdCourseModule = this.moduleService.save(module);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourseModule);
//    }
//
//    @PutMapping("/{moduleId}")
//    public ResponseEntity<CourseModule> updateModule(@PathVariable final long moduleId, @RequestBody final CourseModuleRequest courseModuleRequest){
//        CourseModule updatedModule = this.moduleService.update(moduleId, courseModuleRequest);
//        return ResponseEntity.ok(updatedModule);
//    }
//}
