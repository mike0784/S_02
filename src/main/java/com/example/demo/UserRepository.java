package com.example.demo;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParser;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class UserRepository {
    private final List<User> users;
    public UserRepository() {
        this.users = new ArrayList<>();
        try {
            FileReader f = new FileReader("users.csv");
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(f).withCSVParser(parser).build();
            List<String[]> allData = csvReader.readAll();
            for(String[] row: allData)
            {
                Integer id = Integer.parseInt(row[0]);
                String name = row[1];
                users.add(new User(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        return List.copyOf(users);
    }

    public User getByName(String name) {
        return users.stream()
                .filter(it -> Objects.equals(it.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public User getById(long id) {
        return users.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
