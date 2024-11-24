package com.ustyn.courseproject.controller;

import com.ustyn.courseproject.document.library.Library;
import com.ustyn.courseproject.document.library.LibraryStaff;
import com.ustyn.courseproject.document.literature.Article;
import com.ustyn.courseproject.document.literature.Book;
import com.ustyn.courseproject.document.literature.Literature;
import com.ustyn.courseproject.document.reader.Reader;
import com.ustyn.courseproject.document.reader.Scientist;
import com.ustyn.courseproject.document.reader.Student;
import com.ustyn.courseproject.service.library.LibraryService;
import com.ustyn.courseproject.service.literature.LiteratureService;
import com.ustyn.courseproject.service.reader.ReaderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/aggregation")
public class AggregationsController {
    private final LiteratureService literatureService;
    private final ReaderService readerService;
    private final LibraryService libraryService;

    public AggregationsController(LiteratureService literatureService,
                                  ReaderService readerService, LibraryService libraryService) {
        this.literatureService = literatureService;
        this.readerService = readerService;
        this.libraryService = libraryService;
    }

    @GetMapping("/one")
    public String one() {
        return "aggregations/one";
    }

    @GetMapping("/two")
    public String two(Model model) {

        List<Literature> literatures = literatureService.findAll();
        model.addAttribute("literatures", literatures);
        model.addAttribute("selectedLiterature", literatures.stream().findFirst());
        model.addAttribute("readers", new ArrayList<Reader>());

        return "aggregations/two";
    }

    @PostMapping("/two")
    public String two(@RequestParam("selectedLiterature")Literature selectedLiterature,
                      Model model) {

        List<Reader> readers = readerService.findReadersByBorrowedLiterature(selectedLiterature);

        List<Student> students = readers.stream()
                .filter(reader -> reader instanceof Student)
                .map(reader -> (Student) reader)
                .toList();

        List<Scientist> scientists = readers.stream()
                .filter(reader -> reader instanceof Scientist)
                .map(reader -> (Scientist) reader)
                .toList();

        model.addAttribute("students", students);
        model.addAttribute("scientists", scientists);

        model.addAttribute("literatures", literatureService.findAll());
        model.addAttribute("selectedLiterature", selectedLiterature);

        return "aggregations/two";
    }

    @GetMapping("/three")
    public String three(Model model) {
        List<Library> libraries = libraryService.findAll();
        model.addAttribute("libraries", libraries);
        model.addAttribute("selectedLibrary", libraries.stream().findFirst());
        model.addAttribute("libraryStaffs", new ArrayList<LibraryStaff>());

        return "aggregations/three";
    }

    @PostMapping("/three")
    public String three(@RequestParam("selectedLibrary") Library selectedLibrary,
                        Model model) {
        List<Library> libraries = libraryService.findAll();
        model.addAttribute("libraries", libraries);
        model.addAttribute("selectedLibrary", selectedLibrary);
        model.addAttribute("libraryStaffs", libraryService.findStaffByLibraryId(selectedLibrary.getId()));

        return "aggregations/three";
    }

    @GetMapping("/four")
    public String four(Model model) {

        Set<String> authors = literatureService.findAll().stream().map(Literature::getAuthor).collect(Collectors.toSet());
        model.addAttribute("authors", authors);
        model.addAttribute("selectedAuthor", authors.stream().findFirst());

        List<Literature> literatures = new ArrayList<>();

        List<Book> books = literatures.stream()
                .filter(lit -> lit instanceof Book)
                .map(lit -> (Book) lit)
                .toList();

        List<Article> articles = literatures.stream()
                .filter(lit -> lit instanceof Article)
                .map(lit -> (Article) lit)
                .toList();

        model.addAttribute("books", books);
        model.addAttribute("articles", articles);
        return "aggregations/four";
    }

    @PostMapping("/four")
    public String four(@RequestParam("selectedAuthor") String selectedAuthor,
                        Model model) {
        Set<String> authors = literatureService.findAll().stream().map(Literature::getAuthor).collect(Collectors.toSet());
        model.addAttribute("authors", authors);
        model.addAttribute("selectedAuthor", selectedAuthor);

        List<Literature> literatures = literatureService.findAllByAuthor(selectedAuthor);

        List<Book> books = literatures.stream()
                .filter(lit -> lit instanceof Book)
                .map(lit -> (Book) lit)
                .toList();

        List<Article> articles = literatures.stream()
                .filter(lit -> lit instanceof Article)
                .map(lit -> (Article) lit)
                .toList();

        model.addAttribute("books", books);
        model.addAttribute("articles", articles);

        return "aggregations/four";
    }

    @GetMapping("/five")
    public String five() {
        return "aggregations/five";
    }

    @GetMapping("/six")
    public String six() {
        return "aggregations/six";
    }

    @GetMapping("/seven")
    public String seven() {
        return "aggregations/seven";
    }

    @PostMapping("/seven")
    public String seven(@RequestParam("searchQuery") String searchQuery,
                        Model model) {

        List<Reader> readers = readerService.findByInstitutionOrSpecialty(searchQuery);

        List<Student> students = readers.stream()
                .filter(reader -> reader instanceof Student)
                .map(reader -> (Student) reader)
                .toList();

        List<Scientist> scientists = readers.stream()
                .filter(reader -> reader instanceof Scientist)
                .map(reader -> (Scientist) reader)
                .toList();

        model.addAttribute("students", students);
        model.addAttribute("scientists", scientists);

        return "aggregations/seven";
    }

    @GetMapping("/eight")
    public String eight() {
        return "aggregations/eight";
    }

    @GetMapping("/nine")
    public String nine() {
        return "aggregations/nine";
    }

    @PostMapping("/nine")
    public String nine(@RequestParam("dateQuery") LocalDate dateQuery,
                        Model model) {

        List<Reader> readers = readerService.findReadersNotVisitSince(dateQuery);

        List<Student> students = readers.stream()
                .filter(reader -> reader instanceof Student)
                .map(reader -> (Student) reader)
                .toList();

        List<Scientist> scientists = readers.stream()
                .filter(reader -> reader instanceof Scientist)
                .map(reader -> (Scientist) reader)
                .toList();

        model.addAttribute("dateQuery", dateQuery.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.addAttribute("students", students);
        model.addAttribute("scientists", scientists);

        return "aggregations/nine";
    }

    @GetMapping("/ten")
    public String ten() {
        return "aggregations/ten";
    }

}
