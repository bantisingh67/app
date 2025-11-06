package com.app.carsServices;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
   private int id;
   private String name;
   private String department;
   private int age;
   private double salary;

    public Employee(int id, String name, String department, int age, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }
    @Override
    public String toString() {
        return "Employee {"+
                "id="+ id+
                ", name='"+name+'\''+
                ", Department='"+department+'\''+
                ", Age="+age+
                ", salary="+salary+
                '}';
    }
}

public class EmployeeData {
    public static void main(String[] args) {
        List<Employee> resultData = Arrays.asList(
                new Employee(1, "sonu", "IT", 20, 200000.0),
                        new Employee(1, "sonu", "IT", 21, 300000.0),
                        new Employee(2, "sonu1", "IT BPO", 22, 400000.0),
                        new Employee(3, "sonu2", "IT", 19, 500000.0),
                        new Employee(4, "sonu3", "IT", 18, 600000.0),
                        new Employee(5, "sonu4", "IT Carrier", 24, 700000.0),
                        new Employee(6, "sonu5", "IT Mahendra", 28, 900000.0),
                        new Employee(7, "sonu6", "IT", 23, 300000.0)
        );

//        1.Filter value accourding to age
        System.out.println("Filter value accourding to age");
       List<Employee> resultAge = resultData.stream().filter(e -> e.getAge() > 20).collect(Collectors.toList());
      resultAge.forEach(System.out::println);

//      2.Grouping employee details via department
        System.out.println("Grouping employee details via department");
        Map<String, List<Employee>> collectGroupingg = resultData.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        collectGroupingg.forEach((dept, deptemp) -> {
            System.out.println(dept+" "+deptemp);
        });

        //3.Calculate average salary per department
        System.out.println("Calculate average salary per department");
        Map<String, Double> AverageSalary = resultData.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        AverageSalary.forEach((dept, empSal) -> {
            System.out.printf("%s: %.2f%n", dept, empSal);
        });

        System.out.println("\nEmployees sorted by salary (descending):");
        List<Employee> sortedBySalary = resultData.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        sortedBySalary.forEach(System.out::println);

        // 6. Find any employee from IT department using Optional
        System.out.println("\nAny IT employee:");
        Optional<Employee> itEmployee = resultData.stream()
                .filter(e -> e.getDepartment().equals("IT"))
                .findAny();
        itEmployee.ifPresentOrElse(
                emp -> System.out.println("Found: " + emp),
                () -> System.out.println("No IT employee found")
        );
    }
}