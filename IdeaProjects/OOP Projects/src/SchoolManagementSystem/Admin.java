package SchoolManagementSystem;
public class Admin extends Person{
    String i = "ADM000";
    static int num = 1;
    Admin(String name, String email) {
        super(name, email);
        this.setId(i + num);
        num++;
    }


    void Menu() {
        System.out.println("1. Update Profile");
        System.out.println("2. View all Admins");

        //Student
        System.out.println("3. Add Student");
        System.out.println("4. Remove Student");
        System.out.println("5. Enroll Student in course");
        System.out.println("6. Find Student by Id");
        System.out.println("7. View all Student");

        //Teacher
        System.out.println("8. Add teacher");
        System.out.println("9. Remove Teacher");
        System.out.println("10. Assign teacher to Course");
        System.out.println("11. Find Teacher by Id");
        System.out.println("12. View all Teachers");

        //Course
        System.out.println("13. Add courses");
        System.out.println("14. Remove course");
        System.out.println("15. View all Courses");

        System.out.println("16. Logout");
        System.out.println("17. Exit");
    }
    //Update Admin details
    void updateProfileName(String name) {
            setName(name);
    }
    void updateProfilePassword(String password) {
        setPassword(password);
    }
    void updateProfileEmail(String email) {
        setEmail(email);
    }

    //Add new Student, Courses, Teachers
    Course addCourse(String courseTitle, String courseCode, int courseUnit) {
        Course newCourse = new Course (courseTitle, courseCode, courseUnit);
        return newCourse;
    }
    Student addStudent(String name, String email) {
        Student newStudent = new Student(name, email);
        return newStudent;
    }
    Teacher addTeacher(String name, String email) {
        Teacher newTeacher = new Teacher(name, email);
        return newTeacher;
    }


    void enrollStudentToCourse(Student student, Course course) {
        student.addCourse(course);
    }

    void assignTeacherToCourse(Teacher teacher, Course course) {
        teacher.addCourse(course);
    }
    @Override
    String getDetails() {
        return getid() + " " + getName()+ " " + getEmail();
    }

    @Override
    String getid() {
        return this.getId();
    }
    @Override
    public String toString() {
        return getId()+","+ getName()+"," + getEmail() + "," + getPassword();
    }
}
