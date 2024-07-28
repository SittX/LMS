package org.kst.lms.services;

//@Service
//@RequiredArgsConstructor
//public class ModuleService {
//    private final ModuleRepository moduleRepository;
//    private final CourseRepository courseRepository;
//
//    public CourseModule save(CourseModule courseModule){
//        return this.moduleRepository.save(courseModule);
//    }
//
//    public List<CourseModule> findAll(){
//        return this.moduleRepository.findAll();
//    }
//
//    public CourseModule update(long id, CourseModuleRequest courseModuleRequest){
//        CourseModule module = this.moduleRepository.findById(id)
//                .orElseThrow(()-> new ResourceNotFoundException("Course Module with given Id cannot be found."));
//
//        CourseClass courseClass = this.courseRepository.findById(courseModuleRequest.getCourseId())
//                .orElseThrow(() -> new ResourceNotFoundException("Course with given Id cannot be found."));
//
//        module.setName(courseModuleRequest.getName());
//        module.setDescription(courseModuleRequest.getDescription());
////        module.set(courseClass);
//        return this.moduleRepository.save(module);
//    }
//}
