package com.example.ws.infrastructure.shared.utils;

import com.example.ws.infrastructure.persistence.SpringDataUserRepository;
import com.example.ws.infrastructure.persistence.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final SpringDataUserRepository repository;

    public DataLoader(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        List<UserEntity> users = new ArrayList<>();

        UserEntity user1 = new UserEntity();
        user1.setName("Alice Johnson");
        user1.setEmail("alice.johnson@email.com");

        UserEntity user2 = new UserEntity();
        user2.setName("Bob Smith");
        user2.setEmail("bob.smith@email.com");

        UserEntity user3 = new UserEntity();
        user3.setName("Charlie Brown");
        user3.setEmail("charlie.brown@email.com");

        UserEntity user4 = new UserEntity();
        user4.setName("Raul Bolivar");
        user4.setEmail("raul.bolivar@email.com");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        repository.saveAll(users);
        System.out.println("📌 Users inserted in the database: " + users.size());
    }
}
