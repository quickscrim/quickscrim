//package com.quickscrim.database;
//
////import com.codeup.springblogapp.models.Post;
////import com.codeup.springblogapp.models.User;
////import com.codeup.springblogapp.repositories.PostsRepository;
////import com.codeup.springblogapp.repositories.UserRepository;
//import com.quickscrim.models.User;
//import com.quickscrim.repositories.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//@Component
//public class seeder implements CommandLineRunner {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private final EventRepository eventDao;
//    private final UserRepository userDao;
//
//    @Value("${app.env}")
//    private String environment;
//
//    public seeder(EventRepository eventDao, UserRepository userDao) {
//        this.eventDao = eventDao;
//        this.userDao = userDao;
//    }
//
//    private List<User> seedUsers() {
//        List<User> users = Arrays.asList(
//                new User("edwin", "edwin@mail.com", "edwin"),
//                new User("matt", "matt@mail.com", "matt"),
//                new User("nathan", "nathan@mail.com", "nathan"),
//                new User("harley", "harley@mail.com", "harley")
//        );
//        userDao.save(users);
//        return users;
//    }
//
//    private void seedPosts(List<User> users) {
//        Event longPost = new Event(
//                "Example 1",
//                "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci atque commodi eligendi necessitatibus voluptates. At distinctio dolores minus molestiae mollitia nemo sapiente ut veniam voluptates! Corporis distinctio error quaerat vel!"
//        );
//        List<Event> posts = Arrays.asList(
//                new Event("Title 1", "Body 1"),
//                new Event("Title 2", "Body 2"),
//                new Event("Title 3", "Body 3"),
//                new Event("Example 2", "QWE Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci atque commodi eligendi necessitatibus voluptates. At distinctio dolores minus molestiae mollitia nemo sapiente ut veniam voluptates! Corporis distinctio error quaerat vel!"),
//                new Event("Example 3", "ASD Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci atque commodi eligendi necessitatibus voluptates. At distinctio dolores minus molestiae mollitia nemo sapiente ut veniam voluptates! Corporis distinctio error quaerat vel!"),
//                longPost
//        );
//        Random r = new Random();
//        for (Event e : events) {
//            User randomUser = users.get(r.nextInt(users.size()));
//            e.setUser(randomUser);
//        }
//        eventDao.save(events);
//    }
//
//
//    @Override
//    public void run(String... strings) throws Exception {
//        if (! environment.equals("development")) {
//            log.info("app.env is not development, doing nothing.");
//            return;
//        }
//        log.info("Deleting events...");
//        eventDao.deleteAll();
//        log.info("Deleting users...");
//        userDao.deleteAll();
//        log.info("Seeding users...");
//        List<User> users = seedUsers();
//        log.info("Seeding events...");
//        seedPosts(users);
//        log.info("Finished running seeders!");
//    }
//}
//
