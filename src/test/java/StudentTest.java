
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Ini gunananya untuk ngerubah lifecycle nya jadi PER_CLASS
//
// Kalau makenya PER_CLASS, setiap kali kamu ngejalanin testing, class test nya bakal dibikin cuma sekali, otomatis semua
// Perubahan yang kamu buat di method test nya bakal kebawa. Beda sama defaultnya, PER_METHOD dimana test class buat setiap
// Test method tu cuman dibuat sekali, karena itu perubahan dari tiap test methodnya gk kebawa.

// Nah kalo kamu pake lifecylce PER_CLASS itu kamu bisa make beberapa fungsi tambahan kaya beforeAll, beforeEach, afterAll, dan afterEach
// Buat ngemodifikasi isi test classnya sebelum dibuat.

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTest {
    private List<Student> students;

    //    beforeAll dipanggil sebelum kamu ngelakuin tes, mau itu tes satu satu ataupun semua tes. Ini cuman dipanggil sekali
    @BeforeAll
    public void initClass() {
        students = new ArrayList<>();
    }

    //    beforeEach dipanggil sebelum kamu ngetes tiap method. Kalo kamu ngejalanin tes nya semua sekaligues, nanti sebelum tiap
//    tiap methodnya fungsi ini bakal dipanggil
    @BeforeEach
    public void initMethod() {
        Student student1 = new Student("John", 20);
        Student student2 = new Student("Alice", 22);
        students.add(student1);
        students.add(student2);
    }

    //    Sama kaya beforeEach, tpi kalo ini dipanggil setelah tiap methodnya selesai dipake
    @AfterEach
    public void cleanMethod() {
        students.clear();
    }

    //    Sama kaya beforeAll, tpi kalo ini dipanggil setelah semua testing udah selesai.
    @AfterAll
    public void cleanUp() {
        students.clear();
    }

    @Test
    public void testMethod1TestDataCreation() {
        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    public void testMethod2TestStudentEnrolment() {
        Student student = students.get(0);
        student.enrollCourse("Math");
        student.enrollCourse("Physics");
        assertEquals(2, student.getEnrolledCourses().size());
        assertEquals("Math", student.getEnrolledCourses().get(0));
        assertEquals("Physics", student.getEnrolledCourses().get(1));
        System.out.println(students);
    }

    @Test
    public void testMethod3TestStudentGrade() {
        Student student = students.get(0);
        student.setGrade("Math", "A");
        student.setGrade("Physics", "B");
        assertEquals("A", student.getGrade("Math"));
        assertEquals("B", student.getGrade("Physics"));
    }
}
