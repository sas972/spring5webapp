package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authRepo;
    private final BookRepository bookRepo;
    private final PublisherRepository pubRepo;

    public BootStrapData(AuthorRepository authRepo, BookRepository bookRepo, PublisherRepository pubRepo) {
        this.authRepo = authRepo;
        this.bookRepo = bookRepo;
        this.pubRepo = pubRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher aaa = new Publisher("Sas Kiadó", "Dandár utca 19."
                , "Budapest", "Budapest","1095");

        System.out.println("Number of publisher before save: " + pubRepo.count());
        pubRepo.save(aaa);
        System.out.println("Number of publisher after save: " + pubRepo.count());
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12345678");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(aaa);
        aaa.getBooks().add(ddd);
        authRepo.save(eric);
        bookRepo.save(ddd);
        pubRepo.save(aaa);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("no EJB", "8653256");
        rod.getBooks().add(noEjb);

        noEjb.getAuthors().add(rod);
        noEjb.setPublisher(aaa);
        aaa.getBooks().add(noEjb);
        authRepo.save(rod);
        bookRepo.save(noEjb);
        pubRepo.save(aaa);

        System.out.println("Number of books: " + bookRepo.count());

        System.out.println("Publisher's number of book: " + aaa.getBooks().size());

    }
}
