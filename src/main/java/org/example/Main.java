package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    // Задаем  статичное поле логгера
    static Logger logger;

    // Mетод получения списка персон из JSON-файла
    public static List<Person > getPersons() throws IOException {
        //Создаем объект ObjectMapper для перевода JSON-файла в структуру дерева JSON-node
        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> persons = new ArrayList<>();
        try {
            //Переводим JSON-файл в структуру дерева JSON-node
            JsonNode jsonNode = objectMapper.readTree(new FileReader("persons.json"));
            for (JsonNode person : jsonNode) {
                persons.add(new Person(person.get("name").asText(), person.get("dateOfBirth").asText(),   person.get("email").asText()));
            }
            logger.log(Level.INFO, "JSON файл успешно считан");
        }
        //При необходимости выбрасываем исключение с соответствующим сообщением ошибки в лог-файл
        catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return persons;
    }

    //Метод подсчета среднего возраста людей
    public static int getAverageAge(List<Person> persons) throws IOException {
        int sum = 0;
        for (Person person : persons) {
            sum += person.getAge();
        }
        return sum / persons.size();
    }

    //Метод группировки людей по возрастным группам через API (возвращает массив из трех списков персон)
    public static List<List<Person>> groupPersons(List<Person> persons) throws IOException {
        PersonFilter childrenFilter = person -> person.getAge() < 18;
        List<Person> children = persons.stream().filter(childrenFilter::filter).toList();
        PersonFilter youthFilter = person -> person.getAge() >= 18 && person.getAge() < 45;
        List<Person> youth = persons.stream().filter(youthFilter::filter).toList();
        PersonFilter oldFilter = person -> person.getAge() >= 45;
        List<Person> old = persons.stream().filter(oldFilter::filter).toList();

        return List.of(children, youth, old);
    }

    //Метод фильтрации людей по високосному году рождения
    public static List<Person> getLeapYearPersons(List<Person> persons) throws IOException {
        for (Person person : persons) {
            int year = Integer.parseInt(person.getDateOfBirth().substring(6, 10));
            //если год делится на 400 или делится на 4 но не делится на 100
            if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
                logger.log(Level.INFO, person.getName() + " родился в високосный год");
            }
        }
        return persons;
    }

    // Метод фильтрации персон
    public static List<Person> getFilterPersons(List<Person> persons) throws IOException{

        PersonFilter ageFilter = person -> person.getAge() > 18;
        //Фильтрацию проводим через StreamAPI
        List<Person> filteredPersons = persons.stream().filter(ageFilter::filter).toList();
        for (int i = 0; i < filteredPersons.size(); i++) {
            logger.log(Level.INFO, "Проверки прошел пользователь с именем " + filteredPersons.get(i).getName());
        }
        return filteredPersons;
    }

    //Метод записи списка пользователей в JSON-файл
    public static void writePersons(List<Person> filteredPersons, String namefile) throws IOException {
        //Создаем объект ObjectMapper для перевода JSON-файла в структуру дерева JSON-node
        ObjectMapper objectMapper = new ObjectMapper();
        //Создаем объект ObjectNode узла (записи)
        ObjectNode jsonNode;
        //Создаем JSON-массив
        ArrayNode arrayNode = objectMapper.createArrayNode();

        for (Person person : filteredPersons) {
            jsonNode = objectMapper.createObjectNode();
            jsonNode.put("name", person.getName());
            jsonNode.put("age", person.getAge());
            jsonNode.put("email", person.getEmail());
            //Добавляем узел в JSON-массив
            arrayNode.add(jsonNode);
        }
        //Записываем JSON-массив в файл "filteredUsers.json"
        objectMapper.writeValue(new FileWriter(namefile), arrayNode);
    }

    //метод, который выполняет длительную операцию (например, сортировку большого массива).
    // Измерьте время выполнения и залогируйте его с помощью логгера.
    public static void longOperation(List<Person> persons) throws IOException {
        long start = System.currentTimeMillis();
        // Длительная операция
        List<List<Person>> result = groupPersons(persons);

        long end = System.currentTimeMillis();
        logger.log(Level.INFO, "Время выполнения: " + (end - start) + " мс");
    }

    public static void main(String[] args) throws IOException {
        logger = LoggerConfig.createLogger(Main.class);
        // Получаем список людей из json файда
        List<Person> persons = getPersons();
        //Находим средний возраст
        int AverageAge = getAverageAge(persons);
        logger.log(Level.INFO, "Средний возраст: " + AverageAge);
        // Получаем группы людей по возрасту и записываем их в новые json файлы
        List<List<Person>> groups = groupPersons(persons);
        logger.log(Level.INFO, "Дети: " + groups.get(0));
        logger.log(Level.INFO, "Молодежь: " + groups.get(1));
        logger.log(Level.INFO, "Взрослые: " + groups.get(2));
        writePersons(groups.get(0), "children.json");
        writePersons(groups.get(1), "youth.json");
        writePersons(groups.get(2), "old.json");

        longOperation(persons);

        List<Person> filteredPersons = getFilterPersons(persons);
        writePersons(filteredPersons, "filteredPerson.json");

        List<Person> get = getLeapYearPersons(persons);
    }
}