package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.Subject;
import org.kst.lms.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<Subject> findAll(){
       return this.subjectRepository.findAll().stream()
               .sorted(Comparator.comparingLong(Subject::getId))
               .toList();
    }

    public Subject findById(long id){
        return this.subjectRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Subject not found"));
    }

    public Subject save(Subject subject){
        return this.subjectRepository.save(subject);
    }

   public void delete(long id){
        this.subjectRepository.deleteById(id);
   }

   public Subject upateSubject(long id, Subject updatedSubject){
        Subject originalSubject = this.subjectRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Subject not found"));
        originalSubject.setName(updatedSubject.getName());
        return this.subjectRepository.save(originalSubject);
   }
}
