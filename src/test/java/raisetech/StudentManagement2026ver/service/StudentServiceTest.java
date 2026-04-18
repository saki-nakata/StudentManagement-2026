package raisetech.StudentManagement2026ver.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement2026ver.controller.converter.StudentConverter;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.domain.StudentDetail;
import raisetech.StudentManagement2026ver.exception.NotFoundException;
import raisetech.StudentManagement2026ver.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;
  @Mock
  private StudentConverter converter;
  @InjectMocks
  private StudentService sut;

  private Student createStudent(int id) {
    Student student = new Student();
    student.setId(id);
    return student;
  }

  private StudentCourse createCourse(int studentId) {
    StudentCourse course = new StudentCourse();
    course.setStudentId(studentId);
    return course;
  }

  @Test
  void 受講生詳細一覧_全件取得できること() {
    Student student = createStudent(1);
    List<Student> students = List.of(student);
    List<StudentCourse> courses = List.of(createCourse(1));
    StudentDetail detail = new StudentDetail(student, courses);
    List<StudentDetail> expected = List.of(detail);

    when(repository.getStudents()).thenReturn(students);
    when(repository.getCourses()).thenReturn(courses);
    when(converter.convertStudentDetails(students, courses)).thenReturn(expected);

    List<StudentDetail> actual = sut.getStudentDetails();

    verify(repository).getStudents();
    verify(repository).getCourses();
    verify(converter).convertStudentDetails(students, courses);

    assertEquals(expected, actual);
  }

  @Test
  void 受講生詳細_指定IDの受講生詳細が取得できること() {
    int id = 1234;
    Student student = createStudent(id);
    List<StudentCourse> courses = List.of(createCourse(id));

    when(repository.getStudent(id)).thenReturn(student);
    when(repository.getCoursesByStudentId(id)).thenReturn(courses);

    StudentDetail actual = sut.getStudentDetail(id);

    verify(repository).getStudent(id);
    verify(repository).getCoursesByStudentId(id);

    assertEquals(student, actual.getStudent());
    assertEquals(courses, actual.getCourses());
  }

  @Test
  void 受講生詳細_指定IDの受講生詳細が取得できない場合は例外が発生すること() {
    int id = 9876;
    when(repository.getStudent(id)).thenReturn(null);

    NotFoundException ex = assertThrows(NotFoundException.class, () -> sut.getStudentDetail(id));

    verify(repository).getStudent(id);
    verify(repository, never()).getCoursesByStudentId(id);

    assertThat(ex).hasMessageContaining(String.valueOf(id));
  }

  @Test
  void 受講生詳細登録_受講生情報とコース情報が登録されること() {
    int studentId = 5432;
    Student student = createStudent(studentId);
    StudentCourse course = new StudentCourse();
    course.setStartDate(LocalDate.of(2026, 2, 1));
    StudentDetail detail = new StudentDetail(student, List.of(course));

    sut.registerStudentDetail(detail);

    verify(repository).registerStudent(student);
    verify(repository).registerCourse(course);

    assertEquals(studentId, course.getStudentId());
    assertNotNull(course.getEndDate());
  }

  @Test
  void 受講生詳細登録_受講生情報がない場合は例外が発生すること() {
    StudentCourse course = new StudentCourse();
    StudentDetail detail = new StudentDetail(null, List.of(course));

    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
        () -> sut.registerStudentDetail(detail));

    assertEquals("登録する受講生情報がありません。", ex.getMessage());
  }

  @Test
  void 受講生詳細登録_コース情報がない場合は受講生情報のみ登録されること() {
    int studentId = 5432;
    Student student = createStudent(studentId);
    StudentDetail detail = new StudentDetail(student, null);

    sut.registerStudentDetail(detail);

    verify(repository).registerStudent(student);
    verify(repository, never()).registerCourse(any());
  }

  @Test
  void 受講生詳細登録_コース情報が空の場合は受講生情報のみ登録されること() {
    int studentId = 5432;
    Student student = createStudent(studentId);
    StudentDetail detail = new StudentDetail(student, List.of());

    sut.registerStudentDetail(detail);

    verify(repository).registerStudent(student);
    verify(repository, never()).registerCourse(any());
  }

  @Test
  void コース情報初期化_受講生IDと終了予定日が設定されること() {
    int studentId = 5432;
    LocalDate startDate = LocalDate.of(2026, 2, 1);
    StudentCourse course = new StudentCourse();
    course.setStartDate(startDate);

    sut.initCourse(course, studentId);

    assertEquals(studentId, course.getStudentId());
    assertEquals(startDate.plusYears(1), course.getEndDate());
  }

  @Test
  void 受講生詳細更新_受講生情報とコース情報が更新されること() {
    int id = 6789;
    Student student = createStudent(id);
    StudentCourse course = createCourse(id);
    StudentDetail detail = new StudentDetail(student, List.of(course));

    when(repository.updateStudent(student)).thenReturn(1);
    when(repository.updateCourse(course)).thenReturn(1);

    sut.updateStudentDetail(detail, id);

    verify(repository).updateStudent(student);
    verify(repository).updateCourse(course);

    assertEquals(id, student.getId());
    assertEquals(id, course.getStudentId());
  }

  @Test
  void 受講生情報更新_受講生情報がない場合は何も処理されないこと() {
    int id = 6789;

    sut.updateStudent(null, id);

    verify(repository, never()).updateStudent(any());
  }

  @Test
  void 受講生情報更新_更新件数0の場合は例外が発生すること() {
    int id = 9876;
    Student student = new Student();

    when(repository.updateStudent(student)).thenReturn(0);

    NotFoundException ex = assertThrows(NotFoundException.class,
        () -> sut.updateStudent(student, id));

    verify(repository).updateStudent(student);

    assertEquals(id, student.getId());
    assertThat(ex).hasMessageContaining(String.valueOf(id));
  }

  @Test
  void コース情報更新_コース情報がない場合は何も処理されないこと() {
    int id = 5678;

    sut.updateCourses(null, id);

    verify(repository, never()).updateCourse(any());
  }

  @Test
  void コース情報更新_複数件更新されること() {
    int studentId = 5678;
    StudentCourse course1 = createCourse(studentId);
    course1.setId(8765);
    StudentCourse course2 = createCourse(studentId);
    course2.setId(123456);
    List<StudentCourse> courses = List.of(course1, course2);

    when(repository.updateCourse(course1)).thenReturn(1);
    when(repository.updateCourse(course2)).thenReturn(1);

    sut.updateCourses(courses, studentId);

    verify(repository).updateCourse(course1);
    verify(repository).updateCourse(course2);

    assertEquals(studentId, course1.getStudentId());
    assertEquals(studentId, course2.getStudentId());
  }

  @Test
  void コース情報更新_更新件数0の場合は例外が発生すること() {
    int courseId = 9999;
    int studentId = 5678;
    StudentCourse course = createCourse(studentId);
    course.setId(courseId);
    List<StudentCourse> courses = List.of(course);

    when(repository.updateCourse(course)).thenReturn(0);

    NotFoundException ex = assertThrows(NotFoundException.class,
        () -> sut.updateCourses(courses, studentId));

    verify(repository).updateCourse(course);

    assertThat(ex).hasMessageContaining(String.valueOf(courseId));
  }

}