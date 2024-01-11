# Простой проект для освоения навыков работы с MongoDB

## Dependencies

Использовал [MongoDB Driver](https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync/4.11.1)

## Что может делать приложение?
- создавать базу данных
- создавать таблицы
- добавлять записи в таблицу
- выводить записи согласно заданию

## Packages
### data_source
Здесь хранится класс DbManager, в котором реализованы основные методы работы с БД.
#### Методы
- void insertAll(String dbName, String collectionName, List<Employee> employees) - создает таблицу с работниками и заносит в неё все данные
- void findAll(String dbName, String collectionName) - выводит всю таблицу
- void findNamesAndDates(String dbName, String collectionName) - выводит ФИО сотрудников из таблицы и даты трудоустройства
- void findAllSorted(String dbName, String collectionName) - выводит сотрудников, отсортированных по ЗП
- void findAvgSalary(String dbName, String collectionName) - выводит среднюю ЗП
- void findNamesAndPhones(String dbName, String collectionName) - выводит имена и номера телефонов
- void print(FindIterable<Document> docs, String ... columns) - метод для печати результатов запросов
- String getSpaces(String s) - вспомогательный метод для печати

### models
Здесь хранится класс Employee, созданный для создания сущности "работник" для последующей записи её в базу данных.
### main
Здесь хранится класс Main, в котором мы создаем список "работников" и далее тестируем описанные выше методы.
