package com.quickscrim.database;

import com.quickscrim.models.Event;
import com.quickscrim.models.User;
import com.quickscrim.repositories.EventRepository;
import com.quickscrim.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class seeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final EventRepository eventDao;
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.env}")
    private String environment;

    public seeder(EventRepository eventDao, UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    private List<User> seedUsers() {
        List<User> users = Arrays.asList(
                new User("edwin", "edwin@mail.com", passwordEncoder.encode("edwinedwin")),
                new User("matt", "matt@mail.com", passwordEncoder.encode("mattmatt")),
                new User("nathan", "nathan@mail.com", passwordEncoder.encode("nathannathan")),
                new User("harley", "harley@mail.com", passwordEncoder.encode("harleyharley"))
        );
        userDao.save(users);
        return users;
    }

    private void seedPosts(List<User> users) {
        Event longEvent = new Event(
                "Event 1", "description 1"
        );
        List<Event> events = Arrays.asList(
                new Event("Event 2", "description 2"),
                new Event("Event 3", "description 3"),
                new Event("Event 4", "description 4"),
                new Event("Event 5", "description 5"),
                new Event("Event 6", "description 6"),
                new Event("Event 7", "description 7"),
                longEvent
        );
        Random r = new Random();
        for (Event e : events) {
            User randomUser = users.get(r.nextInt(users.size()));
            e.setEventByUser(randomUser);
        }
        eventDao.save(events);
    }


    @Override
    public void run(String... strings) throws Exception {
        if (! environment.equals("development")) {
            log.info("app.env is not development, doing nothing.");
            return;
        }
        log.info("Deleting events...");
        eventDao.deleteAll();
        log.info("Deleting users...");
        userDao.deleteAll();
        log.info("Seeding users...");
        List<User> users = seedUsers();
        log.info("Seeding events...");
        seedPosts(users);
        log.info("Finished running seeders!");
    }
}

