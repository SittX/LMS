package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.Subject;
import org.kst.lms.services.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    private final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @GetMapping
    public ResponseEntity<List<Subject>> listAllSubjects(){
        logger.info("Querying all subjects");
        List<Subject> subjects = this.subjectService.findAll();
        logger.info("Finished querying all subjects, Total objects : {}", subjects.size());
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable final Long id){
        logger.info("Finding subject with Id : {}", id);
        Subject subject = this.subjectService.findById(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody final Subject subject){
        logger.info("Creating new subject : {}", subject.getName());
        Subject savedSubject = this.subjectService.save(subject);
        return ResponseEntity.ok(savedSubject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable final long id){
        logger.info("Deleting subject with Id : {}", id);
        this.subjectService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable final long id, @RequestBody final Subject subject){
        logger.info("Updating subject with Id : {}", id);
        Subject updatedSubject = this.subjectService.upateSubject(id, subject);
        return ResponseEntity.ok(updatedSubject);
    }
}
