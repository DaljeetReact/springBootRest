package com.singh.singhweb.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service // this is tell the compiler that this is service class
public class StudentService {

    @Autowired // this will automatically connect to StudentRespository
    // with creating object with new keyword
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private  final  StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll(); // Fecth all the Records from database
    }

    public void SaveNewStudent(Student student) {
        Optional<Student> studentOptional= studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw  new IllegalMonitorStateException("Email is already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudentByID(Long stdentId) {
       boolean Exists =  studentRepository.existsById(stdentId);
       if (!Exists){
           throw  new IllegalStateException("Student does not exist with this id");
       }
       studentRepository.deleteAllById(List.of(stdentId));
    }

    @Transactional
    public void UpdateStudentData(Long Id, String name, String email) {
        Student student =  studentRepository.findById(Id).orElseThrow(()->
               new IllegalStateException("Student does not exist with this id")
        );

        if(name != null && name.length() > 0 && Objects.equals(student.getName(),name)){ //name validation check
            try {
                student.setName(name);// update name
            }catch(Exception e){
              new IllegalStateException(e);
            }
        }

        if(email != null && email.length() > 0 && Objects.equals(student.getEmail(),email)){ // email validation check
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email is already taken");
            }

            student.setEmail(email); // update email
        }
    }
}
