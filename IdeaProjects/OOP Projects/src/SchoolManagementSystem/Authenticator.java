package SchoolManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Authenticator {
    static Scanner scanner = new Scanner(System.in);
    public static ResultofLogin<Admin> LoginAdmin(ArrayList<Admin> admins) {

        System.out.print("Enter id: ");
        String id = scanner.nextLine().toUpperCase().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        for(Admin admin1: admins) {
            if(id.equals(admin1.getId())){
                if(password.equals(admin1.getPassword())) {
                    System.out.println("Welcome, " + admin1.getName());
                    return new ResultofLogin<>(true, admin1);
                } else {
                    System.out.println("Wrong password");
                    return new ResultofLogin<>(false, null);
                }
            }
        }
        System.out.println("Id not found");
         return new ResultofLogin<>(false, null);
    }
    public static void SignInAdmin(ArrayList<Admin> admins) {
        Admin admin;
        boolean accountMade = false;
        System.out.print("Enter first name: ");
        String last = scanner.nextLine();
        System.out.print("Enter last name: ");
        String first = scanner.nextLine();
        String name = last + " " + first;
        System.out.print("Enter email: ");
        String email = scanner.nextLine().toLowerCase().trim();
        if(email.contains("@") && email.contains(".")){
            admin = new Admin(name, email);
            System.out.print("Enter a strong password: ");
            String password = scanner.nextLine();
            admin.setPassword(password);
            admins.add(admin);
            accountMade = true;
        } else {
            System.out.println("Enter a valid email");
        }
        if(accountMade) {
            System.out.println("Successfully created an Admin");
            FileHandling.AdminSave(admins);
        }
    }

    public static ResultofLogin<Student> LoginStudent(ArrayList<Student> students){
        System.out.print("Enter id: ");
        String id = scanner.nextLine().toUpperCase().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        for(Student student1: students) {
            if(id.equals(student1.getId()))  {
                if(password.equals(student1.getPassword())) {
                    System.out.println("Welcome, " + student1.getName());
                    return new ResultofLogin<>(true, student1);
                }
                else {
                    System.out.println("Wrong password");
                    return new ResultofLogin<>(false, null);
                }
            }
        }
        System.out.println("Id not found");
        return new ResultofLogin<>(false, null);
    }

    public static ResultofLogin<Teacher> LoginTeacher(ArrayList<Teacher> teachers) {
        System.out.print("Enter id: ");
        String id = scanner.nextLine().toUpperCase().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        for (Teacher teacher : teachers) {
            if (id.equals(teacher.getId())) {
                if (password.equals(teacher.getPassword())) {
                    System.out.println("Welcome, " + teacher.getName());
                    return new ResultofLogin<>(true, teacher);
                } else {
                    System.out.println("Wrong password");
                    return new ResultofLogin<>(false, null);
                }
            }
        }

        System.out.println("Id not found");
        return new ResultofLogin<>(false, null);
    }
}
