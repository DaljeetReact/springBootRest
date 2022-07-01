package com.singh.singhweb.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //this is responsible for data access
public interface StudentRepository extends JpaRepository<Student,Long> {

    //Select Start from student where email = email@gmail.com
   // @Query("SELECT Student.email from Student where  Student.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
