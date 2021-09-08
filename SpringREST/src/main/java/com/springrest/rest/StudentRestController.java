package com.springrest.rest;

import com.springrest.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    static List<Student> list = new ArrayList<>();

    static {
        list.add(new Student("Aarya", "Devarla"));
        list.add(new Student("Upendra", "Pani-puri"));
        list.add(new Student("Teja", "Break-Dancer"));
    }

    @GetMapping("/students")
    public List<Student> getListOfStudents(){

        return list;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if(studentId < 0 || studentId >= list.size())
            throw new StudentNotFoundException("Student ID : "+ studentId);

        return list.get(studentId);
    }


}
