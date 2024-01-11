package data_source;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import models.Employee;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Projections.include;

public class DBManager {

    // создаем таблицу и добавляем всех сотрудников
    public void insertAll(String dbName, String collectionName, List<Employee> employees) {
        try (var mongoClient = MongoClients.create()) {
            var database = mongoClient.getDatabase(dbName);
            var collection = database.getCollection(collectionName);
            for (Employee employee : employees) {
                var emp = new Document(Map.of(
                        "_id", new ObjectId(),
                        "first_name", employee.getFirstName(),
                        "last_name", employee.getLastName(),
                        "email", employee.getEmail(),
                        "phone_number", employee.getPhoneNumber(),
                        "hire_date", employee.getHireDate(),
                        "job_id", employee.getJobId(),
                        "salary", employee.getSalary()));
                collection.insertOne(emp);
            }
            System.out.println("Successfully!" + "\n");
        }
    }

    // выводим всю таблицу
    public void findAll(String dbName, String collectionName) {
        try (var mongoClient = MongoClients.create()) {
            var database = mongoClient.getDatabase(dbName);
            var collection = database.getCollection(collectionName);
            FindIterable<Document> docs = collection.find();
            print(docs, "_id", "first_name", "last_name", "email", "phone_number", "hire_date", "job_id", "salary");
        }
        System.out.println("\n");
    }

    // выводим ФИО и даты трудо-ва
    public void findNamesAndDates(String dbName, String collectionName) {
        try (var mongoClient = MongoClients.create()) {
            var database = mongoClient.getDatabase(dbName);
            var collection = database.getCollection(collectionName);
            FindIterable<Document> docs = collection.find().projection(include("first_name", "last_name", "hire_date"));
            print(docs, "first_name", "last_name", "hire_date");
        }
        System.out.println("\n");
    }

    // выводим сотрудников, отсортировав по ЗП
    public void findAllSorted(String dbName, String collectionName) {
        try (var mongoClient = MongoClients.create()) {
            var database = mongoClient.getDatabase(dbName);
            var collection = database.getCollection(collectionName);
            FindIterable<Document> docs = collection.find().sort(Sorts.ascending("salary"));
            print(docs, "_id", "first_name", "last_name", "email", "phone_number", "hire_date", "job_id", "salary");
        }
        System.out.println("\n");
    }

    // выводим среднюю ЗП
    public void findAvgSalary(String dbName, String collectionName) {
        try (var mongoClient = MongoClients.create()) {
            var database = mongoClient.getDatabase(dbName);
            var collection = database.getCollection(collectionName);
            for (Document document : collection.aggregate(List.of(Aggregates.group(null, Accumulators.avg("avg_salary", "$salary"))))) {
                System.out.println("average salary = " + document.getDouble("avg_salary"));
            }
        }
        System.out.println("\n");
    }

    // выводим имена и телефоны
    public void findNamesAndPhones(String dbName, String collectionName) {
        try (var mongoClient = MongoClients.create()) {
            var database = mongoClient.getDatabase(dbName);
            var collection = database.getCollection(collectionName);
            FindIterable<Document> docs = collection.find().projection(include("first_name", "phone_number"));
            print(docs, "first_name", "phone_number");
        }
        System.out.println("\n");
    }

    // метод для печати запросов
    private void print(FindIterable<Document> docs, String ... columns) {
        for (String s : columns) {
            System.out.print(s + getSpaces(s));
        }
        System.out.println("\n");
        for (Document doc : docs) {
            for (String s : columns) {
                System.out.print(doc.get(s) + getSpaces(doc.get(s).toString()));
            }
            System.out.println();
        }
    }

    // метод для форматирования вывода запроса на экран
    private String getSpaces(String s) {
        StringBuilder builder = new StringBuilder();
        int n = 30 - s.length();
        while (n > 0) {
            builder.append(" ");
            n--;
        }
        return builder.toString();
    }
}
