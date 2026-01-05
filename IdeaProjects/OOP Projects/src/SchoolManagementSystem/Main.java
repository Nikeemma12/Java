package SchoolManagementSystem;

import java.io.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    static ArrayList<Admin> admins = new ArrayList<>();
    static Admin currentAdmin = null;

    static ArrayList<Student> students = new ArrayList<>();
    static Student newStudent = null;
    static Student currentStudent = null;
    static Student removeStudent = null;

    static ArrayList<Teacher> teachers = new ArrayList<>();
    static Teacher currentTeacher = null;
    static Teacher newTeacher = null;
    static Teacher removeTeacher = null;


    static ArrayList<Course> courses = new ArrayList<>();
    static Course currentCourse = null;
    static Course removeCourse = null;
    static Course newCourse = null;

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        //Read existing admin file
        FileHandling.AdminReadSave(admins);
        //Read Teacher Save
        FileHandling.TeacherReadSave(teachers);
        //Read existing student fle
        FileHandling.StudentReadSave(students);
        //Read existing courses file
        FileHandling.CourseReadSave(courses);
        //Read Student Courses Saves
        FileHandling.StudentCourseListReadSave(students, courses);
        //Read Teacher Courses Saves
        FileHandling.TeacherCourseAssignedReadSave(teachers, courses);

        try {
            //Show role menu
            int roleChoice = 0;

            while(roleChoice!=4) {
                System.out.println("Login As:");
                System.out.println("1. Admin");
                System.out.println("2. Teacher");
                System.out.println("3. Student");
                System.out.println("4. Exit");
                System.out.print("Option: ");
                roleChoice = scanner.nextInt();
                scanner.nextLine();
                switch (roleChoice) {
                    case 1 -> {
                        //Administrator

                        String haveAccount;
                        int end = 0 ;
                        while(end != -1 ) {
                            System.out.print("Do you have an account (yes/no)? ");
                            haveAccount = scanner.nextLine().toLowerCase().trim();
                            switch (haveAccount) {
                                //Login
                                case "yes" -> {
                                    ResultofLogin<Admin> result = Authenticator.LoginAdmin(admins);
                                    int choice = 0;
                                    if(result.getSuccess()) {
                                        currentAdmin = result.getUser();
                                        while(choice!=17) {
                                            currentAdmin.Menu();
                                            System.out.print("Enter a choice: ");
                                            choice = scanner.nextInt();
                                            scanner.nextLine();
                                            switch (choice) {
                                                //Updating profile
                                                case 1 -> {
                                                    int updateChoice = 0;
                                                    while (updateChoice!=4) {
                                                        System.out.println("1. Update name");
                                                        System.out.println("2. Update email");
                                                        System.out.println("3. Update password");
                                                        System.out.println("4. Go back");
                                                        System.out.print("Enter choice: ");
                                                        updateChoice = scanner.nextInt();
                                                        scanner.nextLine();
                                                        switch (updateChoice) {
                                                            //Updating Current Admin name
                                                            case 1-> {
                                                                String name;
                                                                System.out.print("Enter last name: ");
                                                                String last = scanner.nextLine().trim();
                                                                System.out.print("Enter first name: ");
                                                                String first = scanner.nextLine().trim();
                                                                if(!first.isEmpty() && !last.isEmpty()) {
                                                                    name = last + " " + first;
                                                                    currentAdmin.setName(name);
                                                                    FileHandling.AdminSave(admins);
                                                                    System.out.println("--------------");
                                                                    System.out.println("Name changed successfully");
                                                                    System.out.println("--------------");
                                                                } else {
                                                                    System.out.println("--------------");
                                                                    System.out.println("Input can't be empty");
                                                                    System.out.println("--------------");
                                                                }

                                                            }
                                                            //Updating Current Admin Email
                                                            case 2-> {
                                                                String email;
                                                                System.out.print("Enter new email: ");
                                                                email = scanner.nextLine().trim();
                                                                if(email.isEmpty()){
                                                                    System.out.println("--------------");
                                                                    System.out.println("Input can't be empty");
                                                                    System.out.println("--------------");
                                                                }
                                                                else if(!email.contains("@") || !email.contains(".")) {
                                                                    System.out.println("--------------");
                                                                    System.out.println("Invalid email");
                                                                    System.out.println("--------------");
                                                                } else {
                                                                    currentAdmin.setEmail(email);
                                                                    FileHandling.AdminSave(admins);
                                                                    System.out.println("--------------");
                                                                    System.out.println("Email changed successfully");
                                                                    System.out.println("--------------");
                                                                }
                                                            }
                                                            //Updating Current Admin Password
                                                            case 3-> {
                                                                String newPassword, confirmPassword;
                                                                System.out.print("Enter new password: ");
                                                                newPassword = scanner.nextLine().trim();
                                                                if(newPassword.isEmpty()) {
                                                                    System.out.println("-----------------------");
                                                                    System.out.println("Password can't be empty");
                                                                    System.out.println("-----------------------");
                                                                } else {
                                                                    System.out.print("Confirm password: ");
                                                                    confirmPassword = scanner.nextLine().trim();
                                                                    if(!newPassword.equals(confirmPassword)) {
                                                                        System.out.println("-----------------------");
                                                                        System.out.println("Password doesn't match");
                                                                        System.out.println("-----------------------");
                                                                    } else {
                                                                        currentAdmin.setPassword(newPassword);
                                                                        System.out.println("--------------");
                                                                        System.out.println("Password changed successfully");
                                                                        System.out.println("--------------");
                                                                        FileHandling.AdminSave(admins);
                                                                    }
                                                                }

                                                            }
                                                            //Go back to main menu
                                                            case 4 -> {
                                                                System.out.println("--------------");
                                                                System.out.println("Changes saved");
                                                                System.out.println("--------------");
                                                            }
                                                            //Wrong choice
                                                            default -> {
                                                                System.out.println("------------------");
                                                                System.out.println("Enter valid choice");
                                                                System.out.println("------------------");
                                                            }
                                                        }
                                                    }
                                                }
                                                //View All Admins
                                                case 2 -> {
                                                    System.out.println("-----------------------");
                                                    for(Admin admin:admins) {
                                                        if(!admins.isEmpty()){
                                                            System.out.println(admin.getDetails());
                                                        } else {
                                                            System.out.println("There are no current admins");
                                                        }
                                                    }
                                                    System.out.println("-----------------------");
                                                }
                                                //Add new Students
                                                case 3 -> {
                                                    String name;
                                                    System.out.print("Enter last name: ");
                                                    String last = scanner.nextLine().trim();
                                                    System.out.print("Enter first name: ");
                                                    String first = scanner.nextLine().trim();
                                                    //Checks valid name of student
                                                    if(last.isEmpty() || first.isEmpty()) {
                                                        System.out.println("--------------------------");
                                                        System.out.println("Name can't be blank space");
                                                        System.out.println("--------------------------");
                                                    } else {
                                                        name = last + " " + first;
                                                        System.out.print("Enter your email: ");
                                                        String email = scanner.nextLine().trim();
                                                        //checks valid email of student
                                                        if(email.isEmpty()){
                                                            System.out.println("---------------------");
                                                            System.out.println("Email can't be blank");
                                                            System.out.println("---------------------");
                                                        }  else if(!email.contains("@") || !email.contains(".")) {
                                                            System.out.println("--------------");
                                                            System.out.println("Invalid email");
                                                            System.out.println("--------------");
                                                        }else {
                                                            //Checks if password is valid
                                                            System.out.print("Enter password: ");
                                                            String password = scanner.nextLine().trim();
                                                            if(password.isEmpty()) {
                                                                System.out.println("-----------------");
                                                                System.out.println("Invalid password");
                                                                System.out.println("-----------------");
                                                            } else {
                                                                System.out.print("Enter course of study: ");
                                                                String courseOfStudy = scanner.nextLine().trim();
                                                                if(courseOfStudy.isEmpty()) {
                                                                    System.out.println("---------------------");
                                                                    System.out.println("Can't be blank space");
                                                                    System.out.println("---------------------");
                                                                } else {
                                                                    newStudent = currentAdmin.addStudent(name, email);
                                                                    newStudent.setCourseOfStudy(courseOfStudy);
                                                                    newStudent.setPassword(password);
                                                                    students.add(newStudent);
                                                                    FileHandling.StudentSave(students);
                                                                    System.out.println("---------------------------");
                                                                    System.out.println("Student added successfully");
                                                                    System.out.println("---------------------------");
                                                                }

                                                            }

                                                        }

                                                    }

                                                }
                                                //Remove Students
                                                case 4 -> {
                                                    System.out.print("Enter student to remove id: ");
                                                    String id = scanner.nextLine().toUpperCase().trim();
                                                    boolean found = false;
                                                    for(Student student:students) {
                                                        if(student.getId().equals(id)) {
                                                            found = true;
                                                            removeStudent = student;
                                                            break;
                                                        }
                                                    }
                                                    if(found) {
                                                        students.remove(removeStudent);
                                                        FileHandling.StudentSave(students);
                                                        System.out.println("-------------------");
                                                        System.out.println("Student removed successfully");
                                                        System.out.println("-------------------");
                                                    } else{
                                                        System.out.println("-------------------");
                                                        System.out.println("Student id not found");
                                                        System.out.println("Enter valid Student Id");
                                                        System.out.println("-------------------");
                                                    }
                                                }
                                                //Enroll student in course
                                                case 5 -> {
                                                    System.out.print("Enter student id: ");
                                                    String id = scanner.nextLine().toUpperCase().trim();
                                                    boolean found = false;
                                                    if(id.isEmpty()){
                                                        System.out.println("-------------------");
                                                        System.out.println("Space can't be blank");
                                                        System.out.println("-------------------");
                                                    } else {
                                                        for(Student student:students) {
                                                            if(student.getId().equals(id)) {
                                                                found = true;
                                                                currentStudent = student;
                                                                break;
                                                            }
                                                        }
                                                        if(found) {
                                                            boolean courseFound = false;
                                                            System.out.println("Student id found");
                                                            System.out.print("Enter Course Code for student to enroll in: ");
                                                            String courseCode = scanner.nextLine().toUpperCase().trim();
                                                            if(courseCode.isEmpty()){
                                                                System.out.println("-------------------");
                                                                System.out.println("Space can't be blank");
                                                                System.out.println("-------------------");
                                                            } else {
                                                                for(Course course:courses) {
                                                                    if(courseCode.equals(course.getCourseCode())) {
                                                                        courseFound = true;
                                                                        currentCourse = course;
                                                                        break;
                                                                    }
                                                                }
                                                                if(courseFound && !currentStudent.getCourseList().contains(currentCourse)) {
                                                                    System.out.println("-------------------------");
                                                                    currentAdmin.enrollStudentToCourse(currentStudent, currentCourse);
                                                                    System.out.println(currentStudent.getCourseList());
                                                                    FileHandling.StudentCourseListSave(students);
                                                                    System.out.println("Course added successfully");
                                                                    System.out.println("------------------------");
                                                                } else if(courseFound && currentStudent.getCourseList().contains(currentCourse)){
                                                                    System.out.println("-------------------------------------------");
                                                                    System.out.println("Student is already enrolled in this course");
                                                                    System.out.println("-------------------------------------------");
                                                                }
                                                                else {
                                                                    System.out.println("-------------------");
                                                                    System.out.println("Invalid course code\nEnter correct Course Code");
                                                                    System.out.println("-------------------");

                                                                }
                                                            }
                                                        } else {
                                                            System.out.println("-------------------");
                                                            System.out.println("Invalid student id");
                                                            System.out.println("-------------------");
                                                        }
                                                    }

                                                }
                                                //Searching for students via ID
                                                case 6 ->{
                                                    System.out.print("Enter student id: ");
                                                    String id=scanner.nextLine().toUpperCase().trim();
                                                    boolean found =false;
                                                    for(Student student:students) {
                                                        if(id.equals(student.getId())) {
                                                            found = true;
                                                            currentStudent = student;
                                                            break;
                                                        }
                                                    }
                                                    if(found) {
                                                        System.out.println("----------------------");
                                                        System.out.println("Student found");
                                                        System.out.println(currentStudent.getDetails());
                                                        System.out.println("----------------------");
                                                    } else {
                                                        System.out.println("----------------------");
                                                        System.out.println("Couldn't find student");
                                                        System.out.println("Invalid Id");
                                                        System.out.println("----------------------");
                                                    }
                                                }
                                                //View All students
                                                case 7 -> {
                                                    System.out.println("--------------------------------------------------------");
                                                    for (Student student: students) {
                                                        if(!students.isEmpty()){
                                                            System.out.println(student.getDetails());
                                                        } else{
                                                            System.out.println("There are no current students");
                                                        }
                                                    }
                                                    System.out.println("--------------------------------------------------------");
                                                }
                                                //Add new Teacher
                                                case 8 -> {
                                                    String name;
                                                    System.out.print("Enter last name: ");
                                                    String last = scanner.nextLine().trim();
                                                    System.out.print("Enter first name: ");
                                                    String first = scanner.nextLine().trim();
                                                    //Checks valid name of student
                                                    if(last.isEmpty() || first.isEmpty()) {
                                                        System.out.println("--------------------------");
                                                        System.out.println("Name can't be blank space");
                                                        System.out.println("--------------------------");
                                                    } else {
                                                        name = last + " " + first;
                                                        System.out.print("Enter your email: ");
                                                        String email = scanner.nextLine().trim();
                                                        //checks valid email of teacher
                                                        if (email.isEmpty()) {
                                                            System.out.println("---------------------");
                                                            System.out.println("Email can't be blank");
                                                            System.out.println("---------------------");
                                                        } else if (!email.contains("@") || !email.contains(".")) {
                                                            System.out.println("--------------");
                                                            System.out.println("Invalid email");
                                                            System.out.println("--------------");
                                                        } else {
                                                            //Checks if password is valid
                                                            System.out.print("Enter password: ");
                                                            String password = scanner.nextLine().trim();
                                                            if (password.isEmpty()) {
                                                                System.out.println("-----------------");
                                                                System.out.println("Invalid password");
                                                                System.out.println("-----------------");
                                                            } else {
                                                                System.out.print("Enter Department: ");
                                                                String department = scanner.nextLine().trim();
                                                                if(department.isEmpty()) {
                                                                    System.out.println("---------------------");
                                                                    System.out.println("Can't be blank space");
                                                                    System.out.println("---------------------");
                                                                } else {
                                                                    newTeacher = currentAdmin.addTeacher(name, email);
                                                                    newTeacher.setPassword(password);
                                                                    newTeacher.setDepartment(department);
                                                                    teachers.add(newTeacher);
                                                                    FileHandling.TeacherSave(teachers);
                                                                    System.out.println("---------------------------");
                                                                    System.out.println("Teacher added successfully");
                                                                    System.out.println("---------------------------");
                                                                }

                                                            }

                                                        }
                                                    }
                                                }
                                                //Remove Teacher
                                                case 9 -> {
                                                    System.out.print("Enter teacher to remove id: ");
                                                    String id = scanner.nextLine().toUpperCase().trim();
                                                    boolean found = false;
                                                    for(Teacher teacher:teachers) {
                                                        if(teacher.getId().equals(id)) {
                                                            found = true;
                                                            removeTeacher = teacher;
                                                            break;
                                                        }
                                                    }
                                                    if(found) {
                                                        teachers.remove(removeTeacher);
                                                        FileHandling.TeacherSave(teachers);
                                                        System.out.println("-------------------");
                                                        System.out.println("Teacher removed successfully");
                                                        System.out.println("-------------------");
                                                    } else{
                                                        System.out.println("-------------------");
                                                        System.out.println("Teacher id not found");
                                                        System.out.println("Enter valid Teacher's Id");
                                                        System.out.println("-------------------");
                                                    }
                                                }
                                                //Assign Teacher to course
                                                case 10 -> {
                                                    System.out.print("Enter teachers id: ");
                                                    String id = scanner.nextLine().toUpperCase().trim();
                                                    boolean found = false;
                                                    if(id.isEmpty()){
                                                        System.out.println("-------------------");
                                                        System.out.println("Space can't be blank");
                                                        System.out.println("-------------------");
                                                    } else {
                                                        for (Teacher teacher : teachers) {
                                                            if (teacher.getId().equals(id)) {
                                                                found = true;
                                                                currentTeacher = teacher;
                                                                break;
                                                            }
                                                        }
                                                        if(found) {
                                                            boolean courseFound = false;
                                                            System.out.println("Teacher is found");
                                                            System.out.print("Enter course's code : ");
                                                            String courseCode = scanner.nextLine().toUpperCase().trim();
                                                            if(courseCode.isEmpty()) {
                                                                System.out.println("-------------------");
                                                                System.out.println("Space can't be blank");
                                                                System.out.println("-------------------");
                                                            } else {
                                                                for(Course course : courses) {
                                                                    if(courseCode.equals(course.getCourseCode())) {
                                                                        courseFound = true;
                                                                        currentCourse = course;
                                                                        break;
                                                                    }
                                                                }
                                                                if(courseFound && !currentTeacher.getCoursesAssigned().contains(currentCourse)){
                                                                    System.out.println("------------------------");
                                                                    currentAdmin.assignTeacherToCourse(currentTeacher, currentCourse);
                                                                    System.out.println(currentTeacher.getCoursesAssigned());
                                                                    FileHandling.TeacherCourseAssignedSave(teachers);
                                                                    System.out.println("Course added successfully");
                                                                    System.out.println("------------------------");
                                                                }
                                                                else if(courseFound && currentTeacher.getCoursesAssigned().contains(currentCourse)){
                                                                    System.out.println("-------------------------------------------");
                                                                    System.out.println("Teacher is already enrolled in this course");
                                                                    System.out.println("-------------------------------------------");
                                                                }
                                                                else {
                                                                    System.out.println("-------------------");
                                                                    System.out.println("Invalid course code\nEnter correct Course Code");
                                                                    System.out.println("-------------------");

                                                                }
                                                            }
                                                        } else {
                                                            System.out.println("---------------------");
                                                            System.out.println("Teacher ID not found");
                                                            System.out.println("Enter a valid ID");
                                                            System.out.println("---------------------");
                                                        }
                                                    }
                                                }
                                                //Searching for Teachers via ID
                                                case 11 -> {
                                                    System.out.print("Enter teacher id: ");
                                                    String id=scanner.nextLine().toUpperCase().trim();
                                                    boolean found =false;
                                                    for(Teacher teacher:teachers) {
                                                        if(id.equals(teacher.getId())) {
                                                            found = true;
                                                            currentTeacher = teacher;
                                                            break;
                                                        }
                                                    }
                                                    if(found) {
                                                        System.out.println("----------------------");
                                                        System.out.println("Teacher found");
                                                        System.out.println(currentTeacher.getDetails());
                                                        System.out.println("----------------------");
                                                    } else {
                                                        System.out.println("----------------------");
                                                        System.out.println("Couldn't find teacher");
                                                        System.out.println("Invalid Id");
                                                        System.out.println("----------------------");
                                                    }
                                                }
                                                //View all Teachers
                                                case 12 -> {
                                                    System.out.println("--------------------------------------------------------");
                                                    for (Teacher teacher: teachers) {
                                                        if(!teachers.isEmpty()){
                                                            System.out.println(teacher.getDetails());
                                                        } else {
                                                            System.out.println("There are no current teachers");
                                                        }
                                                    }
                                                    System.out.println("--------------------------------------------------------");
                                                }
                                                //Add new Course
                                                case 13 -> {
                                                    System.out.print("Enter Course Code: ");
                                                    String courseCode = scanner.nextLine().toUpperCase().trim();
                                                    if (courseCode.isEmpty()) {
                                                        System.out.println("-----------------------------");
                                                        System.out.println("Course code can't be  blank");
                                                        System.out.println("-----------------------------");
                                                    } else {
                                                        System.out.print("Enter Course Title: ");
                                                        String courseTitle = scanner.nextLine().trim().toUpperCase();
                                                        if(courseTitle.isEmpty()) {
                                                            System.out.println("-----------------------------");
                                                            System.out.println("Course title can't be blank");
                                                            System.out.println("-----------------------------");
                                                        } else {
                                                            System.out.print("Enter unit of course: ");
                                                            int courseUnit = scanner.nextInt();
                                                            scanner.nextLine();
                                                            newCourse = currentAdmin.addCourse(courseTitle, courseCode,courseUnit);
                                                            courses.add(newCourse);
                                                            FileHandling.CourseSave(courses);
                                                            System.out.println("--------------------------");
                                                            System.out.println("Course Added Successfully");
                                                            System.out.println("--------------------------");
                                                        }
                                                    }
                                                }
                                                //Remove course
                                                case 14 -> {
                                                    System.out.print("Enter Course code of Course you want to remove: ");
                                                    String removeCourseCode = scanner.nextLine().toUpperCase().trim();
                                                    boolean found = false;
                                                    if(removeCourseCode.isEmpty()) {
                                                        System.out.println("--------------------------");
                                                        System.out.println("Course code can't be blank");
                                                        System.out.println("-------------------------");
                                                    } else {
                                                        for (Course course :courses) {
                                                            if(course.getCourseCode().equals(removeCourseCode)) {
                                                                removeCourse = course;
                                                                found = true;
                                                                break;
                                                            }
                                                        }
                                                        if(found) {
                                                            courses.remove(removeCourse);
                                                            FileHandling.CourseSave(courses);
                                                            System.out.println("-----------------------------");
                                                            System.out.println("Course removed successfully");
                                                            System.out.println("-----------------------------");
                                                            FileHandling.CourseSave(courses);
                                                        } else  {
                                                            System.out.println("------------------------------------------");
                                                            System.out.println("Course not found \n enter valid Course Code");
                                                            System.out.println("------------------------------------------");
                                                        }
                                                    }
                                                }
                                                //View All Courses
                                                case 15 -> {
                                                    System.out.println("--------------------------------------------------------");
                                                    for (Course course: courses) {
                                                        if(!courses.isEmpty()){
                                                            System.out.println(course.toString());
                                                        } else {
                                                            System.out.println("No courses available");
                                                        }
                                                    }
                                                    System.out.println("--------------------------------------------------------");
                                                }
                                                //Log out of admin
                                                case 16 -> {
                                                    System.out.println("Welcome, Admin");
                                                    ResultofLogin<Admin> again = new ResultofLogin<>(false, null);
                                                    while(!again.getSuccess()) {
                                                        again = Authenticator.LoginAdmin(admins);
                                                    }
                                                    currentAdmin = again.getUser();
                                                }
                                                //Exiting entire Admin
                                                case 17-> end = -1;
                                            }
                                        }

                                    }
                                }
                                //Sign up for Admin
                                case "no" -> Authenticator.SignInAdmin(admins);
                                default -> System.out.println("Enter a valid answer");
                            }
                        }
                    }
                    case 2 -> {
                        ResultofLogin<Teacher> result = Authenticator.LoginTeacher(teachers);
                        if(result.getSuccess()) {
                            int choice = 0;
                            currentTeacher = result.getUser();
                            while(choice!=6){
                                currentTeacher.Menu();
                                System.out.print("Pick a choice: ");
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                switch (choice){
                                    //UPDATE DETAILS
                                    case 1->{
                                        int updateChoice = 0;
                                        while(updateChoice!=4) {
                                            System.out.println("1. Update name");
                                            System.out.println("2. Update email");
                                            System.out.println("3. Update password");
                                            System.out.println("4. Go back");
                                            System.out.print("Enter choice: ");
                                            updateChoice = scanner.nextInt();
                                            scanner.nextLine();
                                            switch (updateChoice) {
                                                //Updating Current Teachers name
                                                case 1 -> {
                                                    String name;
                                                    System.out.print("Enter last name: ");
                                                    String last = scanner.nextLine().trim();
                                                    System.out.print("Enter first name: ");
                                                    String first = scanner.nextLine().trim();
                                                    if (!first.isEmpty() && !last.isEmpty()) {
                                                        name = last + " " + first;
                                                        currentTeacher.setName(name);
                                                        FileHandling.TeacherSave(teachers);
                                                        System.out.println("--------------");
                                                        System.out.println("Name changed successfully");
                                                        System.out.println("--------------");
                                                    } else {
                                                        System.out.println("--------------");
                                                        System.out.println("Input can't be empty");
                                                        System.out.println("--------------");
                                                    }
                                                }
                                                //Updating Current Teachers Email
                                                case 2-> {
                                                    String email;
                                                    System.out.print("Enter new email: ");
                                                    email = scanner.nextLine().trim();
                                                    if(email.isEmpty()){
                                                        System.out.println("--------------");
                                                        System.out.println("Input can't be empty");
                                                        System.out.println("--------------");
                                                    }
                                                    else if(!email.contains("@") || !email.contains(".")) {
                                                        System.out.println("--------------");
                                                        System.out.println("Invalid email");
                                                        System.out.println("--------------");
                                                    } else {
                                                        currentTeacher.setEmail(email);
                                                        FileHandling.TeacherSave(teachers);
                                                        System.out.println("--------------");
                                                        System.out.println("Email changed successfully");
                                                        System.out.println("--------------");
                                                    }
                                                }
                                                //Updating Current Teachers Password
                                                case 3-> {
                                                    String newPassword, confirmPassword;
                                                    System.out.print("Enter new password: ");
                                                    newPassword = scanner.nextLine().trim();
                                                    if(newPassword.isEmpty()) {
                                                        System.out.println("-----------------------");
                                                        System.out.println("Password can't be empty");
                                                        System.out.println("-----------------------");
                                                    } else {
                                                        System.out.print("Confirm password: ");
                                                        confirmPassword = scanner.nextLine().trim();
                                                        if(!newPassword.equals(confirmPassword)) {
                                                            System.out.println("-----------------------");
                                                            System.out.println("Password doesn't match");
                                                            System.out.println("-----------------------");
                                                        } else {
                                                            currentTeacher.setPassword(newPassword);
                                                            System.out.println("--------------");
                                                            System.out.println("Password changed successfully");
                                                            System.out.println("--------------");
                                                            FileHandling.TeacherSave(teachers);
                                                        }
                                                    }

                                                }
                                                //Go back to main menu
                                                case 4 -> {
                                                    System.out.println("--------------");
                                                    System.out.println("Changes saved");
                                                    System.out.println("--------------");
                                                }
                                                //Wrong choice
                                                default -> {
                                                    System.out.println("------------------");
                                                    System.out.println("Enter valid choice");
                                                    System.out.println("------------------");
                                                }
                                            }
                                        }
                                    }
                                    //DISPLAY STUDENT DETAILS
                                    case 2-> {
                                        System.out.println("----------------------------");
                                        System.out.println(currentTeacher.getDetails());
                                        System.out.println("----------------------------");
                                    }
                                    //VIEW ALL COURSES
                                    case 3->{
                                        System.out.println("----------------------------");
                                        for (Course all :courses){
                                            System.out.println(all);
                                        }
                                        System.out.println("----------------------------");
                                    }
                                    //VIEW ASSIGNED COURSES
                                    case 4 -> {
                                        System.out.println("----------------------------");
                                        if(currentTeacher.getCoursesAssigned().isEmpty()){
                                            System.out.println("You have not been assigned to any course");
                                        } else{
                                            for (Course all :currentTeacher.getCoursesAssigned()){
                                                System.out.println(all);
                                            }
                                        }
                                        System.out.println("----------------------------");
                                    }
                                    //LOG OUT
                                    case 5->{
                                        ResultofLogin<Teacher> again = new ResultofLogin<>(false, null);
                                        System.out.println("Logged out successfully");
                                        System.out.println("Welcome, Teacher");
                                        while(!again.getSuccess()) {
                                                again = Authenticator.LoginTeacher(teachers);
                                        }
                                        currentTeacher = again.getUser();
                                    }
                                    //EXIT STUDENT
                                    case 6-> System.out.println("....");
                                    default -> System.out.println("Enter a correct choice");
                                }
                            }
                        }
                    }
                    case 3 -> {
                        ResultofLogin<Student> result = Authenticator.LoginStudent(students);
                        if(result.getSuccess()){
                            int choice = 0;
                            currentStudent = result.getUser();
                            while(choice!=8){
                                currentStudent.Menu();
                                System.out.print("Pick a choice: ");
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                switch(choice){
                                    //UPDATE PROFILE
                                    case 1->{
                                        int updateChoice = 0;
                                        while(updateChoice!=4) {
                                            System.out.println("1. Update name");
                                            System.out.println("2. Update email");
                                            System.out.println("3. Update password");
                                            System.out.println("4. Go back");
                                            System.out.print("Enter choice: ");
                                            updateChoice = scanner.nextInt();
                                            scanner.nextLine();
                                            switch (updateChoice) {
                                                //Updating Current Student name
                                                case 1 -> {
                                                    String name;
                                                    System.out.print("Enter last name: ");
                                                    String last = scanner.nextLine().trim();
                                                    System.out.print("Enter first name: ");
                                                    String first = scanner.nextLine().trim();
                                                    if (!first.isEmpty() && !last.isEmpty()) {
                                                        name = last + " " + first;
                                                        currentStudent.setName(name);
                                                        FileHandling.StudentSave(students);
                                                        System.out.println("--------------");
                                                        System.out.println("Name changed successfully");
                                                        System.out.println("--------------");
                                                    } else {
                                                        System.out.println("--------------");
                                                        System.out.println("Input can't be empty");
                                                        System.out.println("--------------");
                                                    }
                                                }
                                                //Updating Current Admin Email
                                                case 2-> {
                                                    String email;
                                                    System.out.print("Enter new email: ");
                                                    email = scanner.nextLine().trim();
                                                    if(email.isEmpty()){
                                                        System.out.println("--------------");
                                                        System.out.println("Input can't be empty");
                                                        System.out.println("--------------");
                                                    }
                                                    else if(!email.contains("@") || !email.contains(".")) {
                                                        System.out.println("--------------");
                                                        System.out.println("Invalid email");
                                                        System.out.println("--------------");
                                                    } else {
                                                        currentStudent.setEmail(email);
                                                        FileHandling.StudentSave(students);
                                                        System.out.println("--------------");
                                                        System.out.println("Email changed successfully");
                                                        System.out.println("--------------");
                                                    }
                                                }
                                                //Updating Current Student Password
                                                case 3-> {
                                                    String newPassword, confirmPassword;
                                                    System.out.print("Enter new password: ");
                                                    newPassword = scanner.nextLine().trim();
                                                    if(newPassword.isEmpty()) {
                                                        System.out.println("-----------------------");
                                                        System.out.println("Password can't be empty");
                                                        System.out.println("-----------------------");
                                                    } else {
                                                        System.out.print("Confirm password: ");
                                                        confirmPassword = scanner.nextLine().trim();
                                                        if(!newPassword.equals(confirmPassword)) {
                                                            System.out.println("-----------------------");
                                                            System.out.println("Password doesn't match");
                                                            System.out.println("-----------------------");
                                                        } else {
                                                            currentStudent.setPassword(newPassword);
                                                            System.out.println("--------------");
                                                            System.out.println("Password changed successfully");
                                                            System.out.println("--------------");
                                                            FileHandling.StudentSave(students);
                                                        }
                                                    }

                                                }
                                                //Go back to main menu
                                                case 4 -> {
                                                    System.out.println("--------------");
                                                    System.out.println("Changes saved");
                                                    System.out.println("--------------");
                                                }
                                                //Wrong choice
                                                default -> {
                                                    System.out.println("------------------");
                                                    System.out.println("Enter valid choice");
                                                    System.out.println("------------------");
                                                }
                                            }
                                        }
                                    }
                                    //VIEW ALL COURSES
                                    case 2->{
                                        System.out.println("----------------------------");
                                        for (Course all :courses){
                                            System.out.println(all);
                                        }
                                        System.out.println("----------------------------");
                                    }
                                    //DISPLAY STUDENT DETAILS
                                    case 3-> System.out.println(currentStudent.getDetails());
                                    //VIEW REGISTERED COURSES
                                    case 4->{
                                        System.out.println("----------------------------");
                                        for(Course c : currentStudent.getCourseList()){
                                            System.out.println(c);
                                        }
                                        System.out.println("----------------------------");
                                    }
                                    //REGISTER COURSES
                                    case 5->{
                                        System.out.print("Enter course code you want to register: ");
                                        String code = scanner.nextLine().toUpperCase().trim();
                                        boolean codeFound = false;
                                        for(Course c :courses){
                                             if (c.getCourseCode().equals(code)) {
                                                 currentCourse = c;
                                                 codeFound = true;
                                                 break;
                                            }
                                        }
                                        if(codeFound){
                                             if(currentStudent.getCourseList().contains(currentCourse)){
                                                 System.out.println("-----------------------------------------");
                                                 System.out.println("Student already enrolled in this course");
                                                 System.out.println("-----------------------------------------");
                                            }
                                            else if(!currentStudent.getCourseList().contains(currentCourse)){
                                                currentStudent.addCourse(currentCourse);
                                                 System.out.println("----------------------------");
                                                 System.out.println("Registered Successfully");
                                                 System.out.println("----------------------------");
                                                 FileHandling.StudentCourseListSave(students);
                                            }
                                        } else {
                                            System.out.println("----------------------------");
                                            System.out.println("Course code not found");
                                            System.out.println("----------------------------");
                                        }
                                    }
                                    //DROP REGISTERED COURSES
                                    case 6->{
                                        System.out.print("Enter course code you want to DROP: ");
                                        String code = scanner.nextLine().toUpperCase().trim();
                                        boolean codeFound = false;
                                        for(Course c :courses){
                                            if (c.getCourseCode().equals(code)) {
                                                currentCourse = c;
                                                codeFound = true;
                                                break;
                                            }
                                        }
                                        if(codeFound){
                                            if(currentStudent.getCourseList().contains(currentCourse)){
                                                currentStudent.getCourseList().remove(currentCourse);
                                                System.out.println("----------------------------");
                                                System.out.println("Course Dropped Successfully");
                                                System.out.println("----------------------------");
                                                FileHandling.StudentCourseListSave(students);
                                            }
                                            else if(!currentStudent.getCourseList().contains(currentCourse)){
                                                System.out.println("------------------------------------");
                                                System.out.println("You are not enrolled in this course");
                                                System.out.println("------------------------------------");
                                            }
                                        } else {
                                            System.out.println("----------------------------");
                                            System.out.println("Course code not found");
                                            System.out.println("----------------------------");
                                        }
                                    }
                                    //LOG OUT
                                    case 7->{
                                        ResultofLogin<Student> again = new ResultofLogin<>(false, null);
                                        System.out.println("Logged out successfully");
                                        System.out.println("Welcome, Student");
                                        while(!again.getSuccess()) {
                                            again = Authenticator.LoginStudent(students);
                                        }
                                        currentStudent = again.getUser();
                                    }
                                    //EXIT STUDENT
                                    case 8-> System.out.println("....");

                                    default -> System.out.println("Enter a correct choice");
                                }
                            }

                        }
                    }
                    case 4-> System.out.println("You have exited the School Management System");
                    default -> System.out.println("Enter a correct choice");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("------------------");
            System.out.println("Wrong Input type");
            System.out.println("------------------");
            scanner.nextLine();
        }

    }





}
