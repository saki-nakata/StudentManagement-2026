package raisetech.StudentManagement2026ver;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagement2026verApplication {

  private String name = "テックん";
  private int age = 6;

  private Map<String, Integer> studentMap = new HashMap<>(Map.of(
      "Saki", 34,
      "Enami", 37
  ));

  public static void main(String[] args) {
    SpringApplication.run(StudentManagement2026verApplication.class, args);
  }

  @GetMapping("/student")
  public String getStudent() {
    return name + "さん " + age + "歳";
  }

  @GetMapping("studentMap")
  public Map<String, Integer> getStudentMap() {
    return studentMap;
  }


  @PostMapping("/student")
  public void setStudentInfo(@RequestParam(required = false) String name,
      @RequestParam(required = false) Integer age) {
    if (name != null) {
      this.name = name;
    }
    if (age != null) {
      this.age = age;
    }
  }

  @PostMapping("/studentMap")
  public void setStudentMap(
      @RequestParam String name,
      @RequestParam Integer age) {

    studentMap.put(name, age);
  }


  @PatchMapping("/studentMap")
  public void studentMapName(@RequestParam String beforeName, @RequestParam String newName) {
    if (studentMap.containsKey(beforeName)) {
      int age = studentMap.get(beforeName);
      studentMap.remove(beforeName);
      studentMap.put(newName, age);
    } else {
      System.err.println("該当するnameがありません。");
    }
  }

  @DeleteMapping("/studentMap")
  public void deleteStudent(@RequestParam String name) {
    studentMap.remove(name);
  }

}
