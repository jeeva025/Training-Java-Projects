package studentmanagement;


import studentmanagement.customexceptions.*;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<Integer, Student> studentsInfo = new HashMap<>();
        HashMap<Integer, Course> courses = new HashMap<>();

        // Initializing courses
        courses.put(1, new Course(1, "Java Full Stack", 25000, 6));
        courses.put(2, new Course(2, "Python Full Stack", 20000, 5));
        courses.put(3, new Course(3, "MERN Stack", 30000, 6));
        courses.put(4, new Course(4,"Frontend Developer", 20000, 3));

        StudentService studentService = new StudentService();
        StudentFileService fileService = new StudentFileService();



        int choice;

        do {
            System.out.println("\n======================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM");
            System.out.println("======================================");

            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student By ID");
            System.out.println("4. Search Student By Name");
            System.out.println("5. Update Student Marks");
            System.out.println("6. Update Attendance");
            System.out.println("7. Calculate Grade");
            System.out.println("8. Remove Student");
            System.out.println("9. Display Topper");
            System.out.println("10. Save Student Details");
            System.out.println("11. Load Student Details");
            System.out.println("12. Exit");

            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice){
                    case 1:
                        System.out.println("------Add Student-----");

                        System.out.print("Enter student ID : ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Student Name : ");
                        String name = sc.nextLine();

                        System.out.print("Enter Student Email : ");
                        String email = sc.nextLine();

                        System.out.print("Enter total Marks out of 500 : ");
                        double marks = sc.nextDouble();

                        System.out.print("Enter attendance : ");
                        int attendance = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter student status (ACTIVE/INACTIVE/COMPLETED ) : ");
                        String status = sc.nextLine();

                        System.out.println("1. Java Full Stack \n2. Python Full Stack \n3. MERN Stack\n4. Frontend Developer");
                        System.out.print("Enter Course Id : ");
                        int courseId = sc.nextInt();

                        Student student = studentService.addStudent(studentsInfo,courses,id,
                                name,
                                email,
                                marks,
                                attendance,
                                status,
                                courseId);

                        studentsInfo.put(id,student);

                        System.out.println("Student added successfully");
                        break;

                    case 2:
                        System.out.println("----All Students----");
                        studentService.displayAllStudents(studentsInfo);
                        break;

                    case 3:
                        System.out.println("------Search Student By ID");
                        System.out.print("Enter Student ID : ");
                        int stdID= sc.nextInt();
                        studentService.searchStudentById(stdID,studentsInfo);

                        break;

                    case 4:
                        System.out.println("-------Search student By Name----- ");
                        System.out.println("Enter Student Name : ");
                        String studentName = sc.nextLine();

                        studentService.searchStudentByName(studentName, studentsInfo);
                        break;

                    case 5:
                        System.out.println("----Update Student Marks");
                        System.out.println("Enter Student id : ");
                        int markId = sc.nextInt();

                        System.out.println("Enter new total Marks out of 500 : ");
                        double newMark = sc.nextDouble();

                        studentService.updateMarks(markId,studentsInfo,newMark);
                        break;

                    case 6:
                        System.out.println("-----Update Attendance ------");
                        System.out.println("Enter student Id: ");
                        int attendanceID =  sc.nextInt();

                        System.out.println("Enter new attendance : ");
                        int newAttendance = sc.nextInt();

                        studentService.updateAttendance(attendanceID,studentsInfo,newAttendance);

                        System.out.println("Attendance Updated");
                        break;

                    case 7:
                        System.out.println("-----Calculate Grade-------");

                        System.out.println("Enter student id : ");
                        int gradeId = sc.nextInt();

                        if (!studentsInfo.containsKey(gradeId)){
                            throw new NoStudentPresent("No student present with id : "+gradeId);
                        }

                        Student gradeStudent = studentsInfo.get(gradeId);

                        String grade = studentService.gradeCalculator(gradeStudent.getMarks());

                        System.out.println("Student : "+gradeStudent.getName());
                        System.out.println("Marks : "+gradeStudent.getMarks());
                        System.out.println("Grade : "+grade);

                        break;

                    case 8:
                        System.out.println("------Remove student -----");
                        System.out.println("Enter Student Id : ");
                        int removeStudent = sc.nextInt();

                        studentService.removeStudent(removeStudent,studentsInfo);
                        break;

                    case 9:
                        System.out.println("-----Displaying Topper-----");

                        studentService.showTopper(studentsInfo);
                        break;

                    case 10:
                        System.out.println("--------Saving Student details-------");
                        fileService.saveStudents(studentsInfo);
                        break;

                    case 11:
                        System.out.println("-----Load Student Details-----");
                        studentsInfo = fileService.loadStudents();
                        break;

                    case 12:
                        System.out.println("Thank you");
                        break;

                    default:
                        System.out.println("Invalid choice. Please select 1 to 12");

                }
            }
            catch (DuplicateIDNotAllowed | NoStudentPresent | InvalidAttendance | InvalidMarks | InvalidCourseId | InvalidStatus e){
                System.out.println("Error : "+e.getMessage());
            }
            catch (Exception e){
                System.out.println("Invalid Input. Please try again");
            }
        }
        while (choice != 12);
    }
}