package raisetech.StudentManagement2026ver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.StudentManagement2026ver.controller.converter.StudentConverter;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.domain.StudentDetail;
import raisetech.StudentManagement2026ver.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/list")
  public String getStudentDetail(Model model) {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> courses = service.searchCourseList();
    model.addAttribute("detailList", converter.convertStudentDetails(students, courses));
    return "student-detail-list";
  }

  @GetMapping("/new")
  public String newStudentDetail(Model model) {
    model.addAttribute("detail", new StudentDetail());
    return "student-detail-register";
  }

  @PostMapping("register")
  public String registerStudentDetail(@ModelAttribute StudentDetail detail, BindingResult result) {
    if (result.hasErrors()) {
      return "student-detail-register";
    }
    service.registerStudentDetail(detail);
    return "redirect:/list";
  }

  @GetMapping("/edit/{id}")
  public String editStudentDetail(@PathVariable int id, Model model) {
    StudentDetail detail = service.getDetail(id);
    model.addAttribute("detail", detail);
    return "student-detail-update";
  }

  @PostMapping("update")
  public String updateStudentDetail(@ModelAttribute StudentDetail detail, BindingResult result) {
    if (result.hasErrors()) {
      return "student-detail-update";
    }
    service.updateStudentDetail(detail);
    return "redirect:/list";

  }

}
