package com.tracker.spring.rest.controller;

import com.tracker.spring.rest.entity.Student;
import com.tracker.spring.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {


    @Autowired
    public MyRestController(StudentService studentService) {
    }

//    @GetMapping("/students")
//    public List<Student> showAllStudents(){
//        return studentService.getAllStudents();
//    }
//    @GetMapping("/")
//    public String getInfoForAll(){
//        return "view_for_all";
//    }
//
//    @GetMapping("/student_task")
//    public String getInfoOnlyForTeamLead(){
//        return "view_for_teamLead";
//    }
//
//    @GetMapping("/student_list")
//    public String getAllStudents(){
//        return "view_for_admin";
//    }
@GetMapping("/")
public ModelAndView getInfoForAll(){
    ModelAndView mav = new ModelAndView("view_for_all");
    return mav;
}

    @GetMapping("/student_task")
    public ModelAndView getInfoOnlyForTeamLead(){
        ModelAndView mav = new ModelAndView("view_for_teamLead");
        return mav;
    }

    @GetMapping("/student_list")
    public ModelAndView getAllStudents(){
        ModelAndView mav = new ModelAndView("view_for_admin");
        return mav;
    }
}
