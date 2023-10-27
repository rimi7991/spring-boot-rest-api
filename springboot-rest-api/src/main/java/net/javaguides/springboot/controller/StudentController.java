package net.javaguides.springboot.controller;

import net.javaguides.springboot.Bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent()
    {
        Student student = new Student(1,"Debarati","Saha");
        //return new ResponseEntity<>(student,HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("customHeader","Debarati")
                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping()
    public ResponseEntity<List<Student>> getStudents()
    {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(2,"Debarati","Saha"));
        students.add(new Student(3,"Ram","Baboo"));
        students.add(new Student(4,"Sham","Sundar"));
        return ResponseEntity.ok(students);
    }

    //Spring Boot REST API with Path Variables
    // http://localhost:8080/students/{id}
    //{id} URI Template Variable
    @GetMapping("{id1}")
    public ResponseEntity<Student> getPathVariables(@PathVariable("id1") int StudentId)
    {
        Student student = new Student(StudentId,"Debarati","Saha");
        return ResponseEntity.ok(student);
    }

    // http://localhost:8080/students/query?id=2&firstName=Debarati&lastName=Saha
    @GetMapping("query")
    public Student requestStudent(@RequestParam int id,
                                  @RequestParam String firstName,
                                  @RequestParam String lastName)
    {
        return new Student(id,firstName,lastName);
    }

    //SpringBoot Application that handles HTTP POST Request
    //@PostMapping and @RequestBody

    //http://localhost:8080/students/create
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> studentCreate(@RequestBody Student student)
    {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }


    //http://localhost:8080/students/{id}/update
    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int StudentId)
    {
        System.out.println("FirstName : "+student.getFirstName());
        System.out.println("LastName : "+student.getLastName());
        return student;
    }

    //http://localhost:8080/students/2/delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId)
    {
        System.out.println("Student Id that is deleted is : "+studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }

}
