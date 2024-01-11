package main;

import data_source.DBManager;
import models.Employee;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // создаем список сотрудников для занесения в базу
        List<Employee> employees = getEmps();

        // создаем название бд и название коллекции, для хранения вышеописанных данных
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название бд: ");
        String dbName = scanner.nextLine();
        System.out.print("Введите название коллекции: ");
        String collectionName = scanner.nextLine();

        // создаем экземпляр DBManager для вз-я с базой
        DBManager dbManager = new DBManager();

        // заносим данные в коллекцию
        dbManager.insertAll(dbName, collectionName, employees);

        // задание 1 - отображение всех данных
        dbManager.findAll(dbName, collectionName);

        // задание 2 - отображение фио и дат найма
        dbManager.findNamesAndDates(dbName, collectionName);

        // задание 3 - отображение всех данных, сортируя по ЗП
        dbManager.findAllSorted(dbName, collectionName);

        // задание 4 - посчитать среднюю зарплату
        dbManager.findAvgSalary(dbName, collectionName);

        // задание 5 - Вывести имена и номера телефонов
        dbManager.findNamesAndPhones(dbName, collectionName);

    }

    public static List<Employee> getEmps() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Steven", "King", "SKING", "515.123.4567", LocalDate.of(1987, 6, 17), "AD_PRES", 32000));
        employees.add(new Employee("Neena", "Konchar", "NIKONCHAR", "515.123.4568", LocalDate.of(1987, 6, 18), "AD_VP", 24000));
        employees.add(new Employee("Lex", "De Haan", "LDEHAAN", "515.123.4569", LocalDate.of(1987, 6, 19), "AD_VP", 24000));
        employees.add(new Employee("Alexander", "Hunold", "AHUNOLD", "515.123.4570", LocalDate.of(1987, 6, 20), "IT_PROG", 9000));
        employees.add(new Employee("Bruce", "Ernst", "BERNST", "515.123.4571", LocalDate.of(1987, 6, 21), "IT_PROG", 9000));
        employees.add(new Employee( "David", "Austin", "DAUSTIN", "515.123.4572", LocalDate.of(1987, 6, 22), "IT_PROG", 9000));
        employees.add(new Employee( "Valli", "Pataballa", "VPATABAL", "515.123.4573", LocalDate.of(1987, 6, 23), "IT_PROG", 9000));
        return employees;
    }
}
