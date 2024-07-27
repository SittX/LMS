package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.Subject;
import org.kst.lms.services.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> listAllSubjects(){
        return ResponseEntity.ok(this.subjectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable final Long id){
        return ResponseEntity.ok(this.subjectService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody final Subject subject){
        return ResponseEntity.ok(this.subjectService.save(subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable final long id){
        this.subjectService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable final long id, @RequestBody final Subject subject){
        Subject updatedSubject = this.subjectService.upateSubject(id, subject);
        return ResponseEntity.ok(updatedSubject);
    }
}
