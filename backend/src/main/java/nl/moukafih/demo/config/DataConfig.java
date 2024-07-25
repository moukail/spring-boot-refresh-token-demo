package nl.moukafih.demo.config;

import nl.moukafih.demo.entity.Author;
import nl.moukafih.demo.entity.Book;
import nl.moukafih.demo.entity.User;
import nl.moukafih.demo.repository.AuthorRepository;
import nl.moukafih.demo.repository.BookRepository;
import nl.moukafih.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public ApplicationRunner databaseInitializer(UserRepository userRepository){
        String password = this.passwordEncoder.encode("pass_1234");
        return (args) -> {
            userRepository.save(new User("admin1@test.com", password,"ROLE_ADMIN"));

            Author author = authorRepository.save(new Author("Juha", "Hinkula"));
            bookRepository.save(new Book("Full Stack Development with Spring Boot 3 and React", author));
        };
    }
}
