package com.singh.singhweb.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student") // i would say base url
public class StudentController {
    private  final StudentService studentService;

    @Autowired // this  will automatically create the [StudentService] instance or object
    // but we need to tell the spring which Service should automatically create its own object to
    //to do so we need to user @Service on the top of the class

    public StudentController(StudentService studentService) {
         //this.studentService =  new StudentService();
        // this is another way to make this work without AutoWired
        // in service we need to add @Component

        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return  this.studentService.getStudents();
    }

    @PostMapping("/save")
    public void  registerStudent(@RequestBody Student student){
       studentService.SaveNewStudent(student);
    }

    @DeleteMapping("/delete/{studentId}")
    public  void  deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudentByID(id);
    }

    @PutMapping("/update/{studentid}")
    public void UpdateStudentById(@PathVariable("studentid") Long Id,
                                  @RequestParam(required = false) String name ,
                                  @RequestParam(required = false) String email){
        studentService.UpdateStudentData(Id,name,email);
    }
}
