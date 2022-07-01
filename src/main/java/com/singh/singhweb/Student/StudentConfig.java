package com.singh.singhweb.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration  //this with configure the student
public class StudentConfig {
    @Bean // create a spring beans
    CommandLineRunner commandLineRunner(StudentRepository repository){
        //CommandLineRunner will hold all the commands
        return  args -> {
          Student Daljeet =  new Student("Daljeet", "Singh@gmail.com", LocalDate.of(1993,8,16));
          Student Gurjeet =  new Student("Gurjeet", "Gurjeet@gmail.com", LocalDate.of(1993,8,16) );


            repository.saveAll(
                    List.of(Daljeet,Gurjeet)
            ); //To Save all the Record in list of array
        };


    }
}
