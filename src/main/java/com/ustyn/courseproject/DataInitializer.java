package com.ustyn.courseproject;

import com.ustyn.courseproject.constants.Roles;
import com.ustyn.courseproject.document.Ticket;
import com.ustyn.courseproject.document.library.Library;
import com.ustyn.courseproject.document.library.LibraryStaff;
import com.ustyn.courseproject.document.literature.Article;
import com.ustyn.courseproject.document.literature.Book;
import com.ustyn.courseproject.document.literature.Literature;
import com.ustyn.courseproject.document.reader.Reader;
import com.ustyn.courseproject.document.reader.Scientist;
import com.ustyn.courseproject.document.reader.Student;
import com.ustyn.courseproject.document.user.Key;
import com.ustyn.courseproject.document.user.Role;
import com.ustyn.courseproject.document.user.User;
import com.ustyn.courseproject.service.library.LibraryService;
import com.ustyn.courseproject.service.libraryStaff.LibraryStaffService;
import com.ustyn.courseproject.service.literature.LiteratureService;
import com.ustyn.courseproject.service.reader.ReaderService;
import com.ustyn.courseproject.service.ticket.TicketService;
import com.ustyn.courseproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final LiteratureService literatureService;
    private final ReaderService readerService;
    private final TicketService ticketService;
    private final LibraryStaffService libraryStaffService;
    LibraryService libraryService;

    @Autowired
    public DataInitializer(UserService userService,
                           LiteratureService literatureService,
                           ReaderService readerService,
                           TicketService ticketService,
                           LibraryStaffService libraryStaffService,
                           LibraryService libraryService) {
        this.userService = userService;
        this.literatureService = literatureService;
        this.readerService = readerService;
        this.ticketService = ticketService;
        this.libraryStaffService = libraryStaffService;
        this.libraryService = libraryService;
    }

    @Override
    public void run(String... args) {
        addLiterature();
        addReaders();
        addLibraryStaff();
        addLibraries();
        addUsers();
    }

    private void addLiterature() {
        List<Literature> literatures = List.of(
                new Book("Кобзар", "Тарас Шевченко", "ISBN-001", true, 500),
                new Book("Тіні забутих предків", "Михайло Коцюбинський", "ISBN-002", true, 300),
                new Book("Чорна Рада", "Пантелеймон Куліш", "ISBN-003", true, 400),
                new Book("Лісова пісня", "Леся Українка", "ISBN-004", true, 200),
                new Book("Хіба ревуть воли, як ясла повні?", "Панас Мирний", "ISBN-005", true, 350),
                new Article("Українська революція 1917-1921", "Іван Липа", "ART-001", true, LocalDate.of(2010, 5, 15)),
                new Article("Історія Галицької держави", "Юрій Шевельов", "ART-002", true, LocalDate.of(2008, 7, 20)),
                new Article("Сучасна українська поезія", "Оксана Забужко", "ART-003", true, LocalDate.of(2015, 3, 10)),
                new Article("Політичні ідеї Сковороди", "Микола Гоголь", "ART-004", true, LocalDate.of(2012, 11, 5)),
                new Article("Внесок Франка в українську культуру", "Іван Франко", "ART-005", true, LocalDate.of(2018, 1, 25))
        );

        literatures.forEach(literature -> {
            if (!literatureService.existsByTitle(literature.getTitle())) {
                literatureService.save(literature);
            }
        });
    }

    private void addReaders() {

        if (!readerService.existsByName("Олександр Петренко")) {

            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Кобзар")));
            Ticket savedTicket = ticketService.save(ticket);

            Student student = new Student("Олександр Петренко", "вул. Грушевського 5", savedTicket, LocalDate.of(2001, 9, 15), "Київський університет");

            readerService.save(student);
        }

        if (!readerService.existsByName("Марія Іваненко")) {
            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Тіні забутих предків")));
            Ticket savedTicket = ticketService.save(ticket);

            Student student = new Student("Марія Іваненко", "вул. Сагайдачного 10", savedTicket, LocalDate.of(2000, 3, 10), "Львівський університет");

            readerService.save(student);
        }

        if (!readerService.existsByName("Іван Ковальчук")) {
            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Чорна Рада")));
            Ticket savedTicket = ticketService.save(ticket);

            Student student = new Student("Іван Ковальчук", "вул. Шевченка 12", savedTicket, LocalDate.of(1999, 7, 20), "Харківський університет");

            readerService.save(student);
        }

        if (!readerService.existsByName("Наталія Сидоренко")) {
            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Лісова пісня")));
            Ticket savedTicket = ticketService.save(ticket);

            Student student = new Student("Наталія Сидоренко", "вул. Франка 25", savedTicket, LocalDate.of(2002, 11, 5), "Одеський університет");

            readerService.save(student);
        }

        if (!readerService.existsByName("Андрій Головко")) {
            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Хіба ревуть воли, як ясла повні?")));
            Ticket savedTicket = ticketService.save(ticket);

            Student student = new Student("Андрій Головко", "вул. Лесі Українки 30", savedTicket, LocalDate.of(2001, 5, 15), "Дніпровський університет");

            readerService.save(student);
        }

        if (!readerService.existsByName("Д-р Анна Ткаченко")) {
            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Українська революція 1917-1921")));
            Ticket savedTicket = ticketService.save(ticket);

            Scientist scientist = new Scientist("Д-р Анна Ткаченко", "вул. Хмельницького 15", savedTicket, LocalDate.of(1980, 8, 25), "Біологія");

            readerService.save(scientist);
        }

        if (!readerService.existsByName("Д-р Сергій Лисенко")) {
            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Історія Галицької держави")));
            Ticket savedTicket = ticketService.save(ticket);

            Scientist scientist = new Scientist("Д-р Сергій Лисенко", "вул. Сковороди 18", savedTicket, LocalDate.of(1975, 4, 12), "Історія");

            readerService.save(scientist);
        }

        if (!readerService.existsByName("Д-р Ольга Верещук")) {
            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Сучасна українська поезія")));
            Ticket savedTicket = ticketService.save(ticket);

            Scientist scientist = new Scientist("Д-р Ольга Верещук", "вул. Шептицького 20", savedTicket, LocalDate.of(1985, 10, 20), "Філологія");

            readerService.save(scientist);
        }

        if (!readerService.existsByName("Д-р Дмитро Іванов")) {
            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Політичні ідеї Сковороди")));
            Ticket savedTicket = ticketService.save(ticket);

            Scientist scientist = new Scientist("Д-р Дмитро Іванов", "вул. Драгоманова 8", savedTicket, LocalDate.of(1978, 2, 15), "Фізика");

            readerService.save(scientist);
        }

        if (!readerService.existsByName("Д-р Юлія Кравченко")) {
            Ticket ticket = new Ticket(List.of(literatureService.findByTitle("Внесок Франка в українську культуру")));
            Ticket savedTicket = ticketService.save(ticket);

            Scientist scientist = new Scientist("Д-р Юлія Кравченко", "вул. Січових Стрільців 22", savedTicket, LocalDate.of(1982, 6, 5), "Хімія");

            readerService.save(scientist);
        }
    }

    private void addLibraryStaff() {
        List<LibraryStaff> staff = List.of(
                new LibraryStaff("Олексій Бондар"),
                new LibraryStaff("Катерина Шевченко"),
                new LibraryStaff("Володимир Лісовий"),
                new LibraryStaff("Ірина Кравчук")
        );

        staff.forEach(member -> {
            if (!libraryStaffService.existsByName(member.getName())) {
                libraryStaffService.save(member);
            }
        });

    }

    private void addLibraries() {
        List<Literature> literatures = literatureService.findAll();
        List<Reader> readers = readerService.findAll();
        List<LibraryStaff> staff = libraryStaffService.findAll();

        if (!libraryService.existsByName("Бібліотека імені Василя")) {
            Library library1 = new Library(
                    "Бібліотека імені Василя",
                    literatures.subList(0, 5),
                    staff.subList(0, 2),
                    readers.subList(0, 5)
            );
            libraryService.save(library1);
        }

        if (!libraryService.existsByName("Бібліотека імені Юрія")) {
            Library library2 = new Library(
                    "Бібліотека імені Юрія",
                    literatures.subList(5, 10),
                    staff.subList(2, 4),
                    readers.subList(5, 10)
            );
            libraryService.save(library2);
        }

    }


    private void addUsers() {
        if(!userService.existsByUsername("admin")) {
            Key key1 = new Key("Test123");
            User adminUser = new User("admin", key1, true, List.of(new Role(Roles.ROLE_ADMIN.getValue())));
            userService.save(adminUser);
        }
        if(!userService.existsByUsername("operator")) {
            Key key2 = new Key("Something");
            User operatorUser = new User("operator", key2, true, List.of(new Role(Roles.ROLE_OPERATOR.getValue())));
            userService.save(operatorUser);
        }
        if(!userService.existsByUsername("guest")) {
            Key key3 = new Key("BibaBoba");
            User guestUser = new User("guest", key3, true, List.of(new Role(Roles.ROLE_GUEST.getValue())));
            userService.save(guestUser);
        }
    }
}
