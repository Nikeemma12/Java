package SchoolManagementSystem;

import java.io.*;
import java.util.ArrayList;

public class FileHandling {

    public static void AdminSave(ArrayList<Admin> admins) {

        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\AdminFile.csv";

        try(FileWriter adminFile = new FileWriter(path)) {
            adminFile.write("ID,Name,Email,Password");
            adminFile.write("\n");
            for (Admin admin1: admins) {
                adminFile.write(admin1.toString());
                adminFile.write("\n");
            }

        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void AdminReadSave(ArrayList<Admin> admins) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\AdminFile.csv";
        try(BufferedReader adminFile = new BufferedReader(new FileReader(path))) {
            adminFile.readLine();
            String line;
            while((line = adminFile.readLine()) != null) {
                String[] parts = line.split(",");
                Admin admin = new Admin(parts[1].trim(), parts[2].trim());
                admin.setId(parts[0].trim());
                admin.setPassword(parts[3].trim());
                admins.add((admin));
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

    }
    public static void TeacherSave(ArrayList<Teacher> teachers){
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\TeacherFile.csv";
        try(FileWriter teacherFile = new FileWriter(path)){
            teacherFile.write("ID,Name,Email,Password,Department");
            teacherFile.write("\n");
            for(Teacher teacher : teachers){
                teacherFile.write(teacher.toString());
                teacherFile.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void TeacherReadSave(ArrayList<Teacher> teachers) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\TeacherFile.csv";
        try(BufferedReader teacherFile = new BufferedReader(new FileReader(path))) {
            teacherFile.readLine();
            String line;
            while((line = teacherFile.readLine()) != null) {
                String[] parts = line.split(",");
                Teacher teacher = new Teacher(parts[1].trim(), parts[2].trim());
                teacher.setId(parts[0].trim());
                teacher.setPassword(parts[3].trim());
                teacher.setDepartment(parts[4].trim());
                teachers.add((teacher));
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void StudentSave(ArrayList<Student> students) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\StudentDetailsFile.csv";
        try(FileWriter studentFile = new FileWriter(path)){
            studentFile.write("ID,Name,Email,Password,Course Of Study");
            studentFile.write("\n");
            for (Student student: students) {
                studentFile.write(student.toString());
                studentFile.write("\n");
            }
        }catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void StudentReadSave(ArrayList<Student> students) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\StudentDetailsFile.csv";
        try(BufferedReader studentFile = new BufferedReader(new FileReader(path))) {
            studentFile.readLine();
            String line;
            while((line = studentFile.readLine()) != null) {
                String[] parts = line.split(",");
                Student student = new Student(parts[1].trim(), parts[2].trim());
                student.setId(parts[0].trim());
                student.setPassword(parts[3].trim());
                student.setCourseOfStudy(parts[4].trim());
                students.add((student));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void CourseSave(ArrayList<Course> courses) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\CourseFile.txt";
        try(FileWriter courseFile = new FileWriter(path)){
            for (Course course: courses) {
                courseFile.write(course.toString());
                courseFile.write("\n");
            }
        }catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void CourseReadSave(ArrayList<Course> courses) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\CourseFile.txt";
        try(BufferedReader courseFile = new BufferedReader(new FileReader(path))) {
            String line, courseTitle, courseCode;
            int courseUnit ;
            while((line = courseFile.readLine()) != null) {
                courseCode = line.substring(0, line.indexOf("-")).trim();
                courseTitle = line.substring(line.indexOf("-")+1, line.lastIndexOf(" ")).trim();
                courseUnit = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1, line.lastIndexOf(" ") + 2));
                Course course = new Course (courseTitle, courseCode, courseUnit);
                courses.add(course);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void StudentCourseListSave(ArrayList<Student> students) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\StudentsCourses.csv";
        try(FileWriter studentCourseList = new FileWriter(path)) {
            for (Student student:students) {
                studentCourseList.write(student.getId() + "," + student.getStudentCourseCode().trim());
                studentCourseList.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void StudentCourseListReadSave(ArrayList<Student> students, ArrayList<Course> courses) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\StudentsCourses.csv";
        try(BufferedReader studentCourseList = new BufferedReader(new FileReader(path))) {
            String line;
            String[] parts;
            Student stud = null;
            Course cours;
            while((line = studentCourseList.readLine()) != null) {
                parts=line.split(",");
                String studentID = parts[0];
                for (Student student: students){
                    if(student.getId().equals(studentID)){
                        stud = student;
                        break;
                    }
                }
                for(Course course : courses) {
                    for(int i=1; i<parts.length /*3*/; i++) {
                        if(course.getCourseCode().equals(parts[i])){
                            cours = course;
                            assert stud != null;
                            stud.addCourse(cours);
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void TeacherCourseAssignedSave(ArrayList<Teacher> teachers) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\TeachersAssignedCourses.csv";
        try(FileWriter teacherAssignedCourses = new FileWriter(path)) {
            for(Teacher teacher: teachers) {
                teacherAssignedCourses.write(teacher.getid() + "," + teacher.getTeachersCourseListCode().trim());
                teacherAssignedCourses.write("\n");
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public static void TeacherCourseAssignedReadSave(ArrayList<Teacher> teachers, ArrayList<Course> courses) {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\SchoolManagementSystem\\SavedFiles\\TeachersAssignedCourses.csv";
        try(BufferedReader teacherAssignedCourses = new BufferedReader(new FileReader(path))) {
            String line;
            String[] parts;
            Teacher teach = null;
            Course cours;
            while((line=teacherAssignedCourses.readLine())!=null){
                parts = line.split(",");
                String teacherID = parts[0].trim();
                for(Teacher teacher:teachers) {
                    if(teacherID.equals(teacher.getid())){
                        teach = teacher;
                        break;
                    }
                }
                for(Course course : courses) {
                    for(int i=1; i<parts.length; i++) {
                        if(course.getCourseCode().equals(parts[i])){
                            cours = course;
                            assert teach != null;
                            teach.addCourse(cours);
                        }
                    }
                }
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
