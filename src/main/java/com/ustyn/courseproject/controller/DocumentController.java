package com.ustyn.courseproject.controller;

import com.ustyn.courseproject.constants.Roles;
import com.ustyn.courseproject.document.library.Library;
import com.ustyn.courseproject.document.library.LibraryStaff;
import com.ustyn.courseproject.document.literature.Article;
import com.ustyn.courseproject.document.literature.Book;
import com.ustyn.courseproject.document.literature.Literature;
import com.ustyn.courseproject.document.reader.Reader;
import com.ustyn.courseproject.document.reader.Scientist;
import com.ustyn.courseproject.document.reader.Student;
import com.ustyn.courseproject.document.user.User;
import com.ustyn.courseproject.dto.library.LibraryDto;
import com.ustyn.courseproject.dto.library.LibraryStaffDto;
import com.ustyn.courseproject.dto.reader.ScientistDto;
import com.ustyn.courseproject.dto.reader.StudentDto;
import com.ustyn.courseproject.dto.UserDto;
import com.ustyn.courseproject.dto.literature.ArticleDto;
import com.ustyn.courseproject.dto.literature.BookDto;
import com.ustyn.courseproject.repository.KeyRepository;
import com.ustyn.courseproject.service.library.LibraryService;
import com.ustyn.courseproject.service.libraryStaff.LibraryStaffService;
import com.ustyn.courseproject.service.literature.LiteratureService;
import com.ustyn.courseproject.service.reader.ReaderService;
import com.ustyn.courseproject.service.role.RoleService;
import com.ustyn.courseproject.service.user.UserService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {

    private final UserService userService;
    private final RoleService roleService;
    private final KeyRepository keyRepository;
    private final ReaderService readerService;
    private final LiteratureService literatureService;
    private final LibraryStaffService libraryStaffService;
    private final LibraryService libraryService;

    @Autowired
    public DocumentController(UserService userService,
                              RoleService roleService,
                              KeyRepository keyRepository,
                              ReaderService readerService,
                              LiteratureService literatureService,
                              LibraryStaffService libraryStaffService,
                              LibraryService libraryService) {
        this.userService = userService;
        this.roleService = roleService;
        this.keyRepository = keyRepository;
        this.readerService = readerService;
        this.literatureService = literatureService;
        this.libraryStaffService = libraryStaffService;
        this.libraryService = libraryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }



    // user mappings
    @GetMapping("/users")
    public String users(Model model) {

        model.addAttribute("users", userService.findAll());
        model.addAttribute("formUser", new UserDto());

        model.addAttribute("roles", Roles.asList());
        model.addAttribute("selectedRole", Roles.ROLE_GUEST.getValue());

        return "documents/users-list";
    }

    @GetMapping("users/{id}")
    public String updateUser(@PathVariable String id, Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("formUser", new UserDto(userService.findById(id)));

        model.addAttribute("roles", Roles.asList());
        model.addAttribute("selectedRole", Roles.ROLE_GUEST.getValue());

        return "documents/users-list";
    }

    @PostMapping("/users/{id}")
    public String deleteUser(@PathVariable String id) {
        System.out.println("delete user called");

        userService.deleteById(id);

        return "redirect:/document/users";
    }

    @PostMapping("/users")
    public String saveUser(@Valid @ModelAttribute("formUser") UserDto userDto,
                        BindingResult bindingResult,
                        Model model) {

        if(thereAreErrorsIn(bindingResult, List.of("username"))) {

            model.addAttribute("users", userService.findAll());
            model.addAttribute("formUser", userDto);

            model.addAttribute("roles", Roles.asList());
            model.addAttribute("selectedRole", Roles.ROLE_GUEST.getValue());

            return "documents/users-list";
        }

        User user = new User(userDto);
        userService.save(user);

        return "redirect:/document/users";
    }



    // role and key mappings
    @GetMapping("/roles")
    public String roles(Model model) {

        model.addAttribute("roles", roleService.findAll());

        return "documents/roles-list";
    }


    @GetMapping("/keys")
    public String keys(Model model) {

        model.addAttribute("keys", keyRepository.findAll());

        return "documents/keys-list";
    }



    // reader mappings
    @GetMapping("/readers")
    public String readers(Model model) {
        List<Reader> readers = readerService.findAll();

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

        model.addAttribute("formStudent", new StudentDto());
        model.addAttribute("formScientist", new ScientistDto());

        return "documents/readers-list";
    }


    // student mappings
    @GetMapping("/readers/student/{id}")
    public String updateStudent(@PathVariable String id,
                          Model model) {

        List<Reader> readers = readerService.findAll();

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

        model.addAttribute("formStudent", new StudentDto((Student) readerService.findById(id)));
        model.addAttribute("formScientist", new ScientistDto());

        return "documents/readers-list";
    }

    @PostMapping("/readers/student/{id}")
    public String deleteStudent(@PathVariable String id) {

        readerService.deleteById(id);

        return "redirect:/document/readers";
    }

    @PostMapping("/readers/student")
    public String saveStudent(@Valid @ModelAttribute("formStudent") StudentDto studentDto,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            List<Reader> readers = readerService.findAll();

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

            model.addAttribute("formStudent", studentDto);
            model.addAttribute("formScientist", new ScientistDto());

            return "documents/readers-list";
        }

        Student student = new Student(studentDto);
        readerService.save(student);

        return "redirect:/document/readers";
    }



    // scientist mappings
    @GetMapping("/readers/scientist/{id}")
    public String updateScientist(@PathVariable String id,
                          Model model) {
        List<Reader> readers = readerService.findAll();

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

        model.addAttribute("formStudent", new StudentDto());
        model.addAttribute("formScientist", new ScientistDto((Scientist) readerService.findById(id)));

        return "documents/readers-list";
    }

    @PostMapping("/readers/scientist/{id}")
    public String deleteScientist(@PathVariable String id) {

        readerService.deleteById(id);

        return "redirect:/document/readers";
    }

    @PostMapping("/readers/scientist")
    public String saveScientist(@Valid @ModelAttribute("formScientist") ScientistDto scientistDto,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            List<Reader> readers = readerService.findAll();

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

            model.addAttribute("formStudent", new StudentDto());
            model.addAttribute("formScientist", scientistDto);

            return "documents/readers-list";
        }

        Scientist scientist = new Scientist(scientistDto);
        readerService.save(scientist);

        return "redirect:/document/readers";
    }



    // Literature mappings
    @GetMapping("/literatures")
    public String literatures(Model model) {
        List<Literature> literatures = literatureService.findAll();

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

        model.addAttribute("formBook", new BookDto());
        model.addAttribute("formArticle", new ArticleDto());

        return "documents/literatures-list";
    }

    // Book mappings
    @GetMapping("/literatures/book/{id}")
    public String updateBook(@PathVariable String id,
                             Model model) {

        List<Literature> literatures = literatureService.findAll();

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

        model.addAttribute("formBook", new BookDto((Book) literatureService.findById(id)));
        model.addAttribute("formArticle", new ArticleDto());

        return "documents/literatures-list";
    }

    @PostMapping("/literatures/book/{id}")
    public String deleteBook(@PathVariable String id) {

        literatureService.deleteById(id);

        return "redirect:/document/literatures";
    }

    @PostMapping("/literatures/book")
    public String saveBook(@Valid @ModelAttribute("formBook") BookDto bookDto,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            List<Literature> literatures = literatureService.findAll();

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

            model.addAttribute("formBook", bookDto);
            model.addAttribute("formArticle", new ArticleDto());

            return "documents/literatures-list";
        }

        Book book = new Book(bookDto);
        literatureService.save(book);

        return "redirect:/document/literatures";
    }


    // Article mappings
    @GetMapping("/literatures/article/{id}")
    public String updateArticle(@PathVariable String id,
                                Model model) {

        List<Literature> literatures = literatureService.findAll();

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

        model.addAttribute("formBook", new BookDto());
        model.addAttribute("formArticle", new ArticleDto((Article) literatureService.findById(id)));

        return "documents/literatures-list";
    }

    @PostMapping("/literatures/article/{id}")
    public String deleteArticle(@PathVariable String id) {

        literatureService.deleteById(id);

        return "redirect:/document/literatures";
    }

    @PostMapping("/literatures/article")
    public String saveArticle(@Valid @ModelAttribute("formArticle") ArticleDto articleDto,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            List<Literature> literatures = literatureService.findAll();

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

            model.addAttribute("formBook", new BookDto());
            model.addAttribute("formArticle", articleDto);

            return "documents/literatures-list";
        }

        Article article = new Article(articleDto);
        literatureService.save(article);

        return "redirect:/document/literatures";
    }



    // library-staff mappings
    @GetMapping("/library-staffs")
    public String libraryStaffs(Model model) {

        model.addAttribute("libraryStaffs", libraryStaffService.findAll());
        model.addAttribute("formLibraryStaff", new LibraryStaff());

        return "documents/library-staffs-list";
    }

    @GetMapping("/library-staffs/{id}")
    public String updateLibraryStaff(@PathVariable String id, Model model) {

        model.addAttribute("libraryStaffs", libraryStaffService.findAll());
        model.addAttribute("formLibraryStaff", new LibraryStaffDto(libraryStaffService.findById(new ObjectId(id))));

        return "documents/library-staffs-list";

    }

    @PostMapping("/library-staffs/{id}")
    public String deleteLibraryStaff(@PathVariable String id) {

        libraryStaffService.deleteById(new ObjectId(id));

        return "redirect:/document/library-staffs";
    }

    @PostMapping("/library-staffs")
    public String saveLibraryStaff(@Valid @ModelAttribute("formLibraryStaff") LibraryStaffDto libraryStaffDto,
                                   BindingResult bindingResult,
                                   Model model) {
        if(bindingResult.hasErrors()) {

            model.addAttribute("libraryStaffs", libraryStaffService.findAll());
            model.addAttribute("formLibraryStaff", libraryStaffDto);

            return "documents/library-staffs-list";
        }

        LibraryStaff libraryStaff = new LibraryStaff(libraryStaffDto);
        libraryStaffService.save(libraryStaff);

        return "redirect:/document/library-staffs";
    }



    // library mappings
    @GetMapping("/libraries")
    public String libraries(Model model) {

        model.addAttribute("libraries", libraryService.findAll());
        model.addAttribute("formLibrary", new LibraryDto());

        return "documents/libraries-list";
    }

    @GetMapping("/libraries/{id}")
    public String updateLibrary(@PathVariable String id, Model model) {
        model.addAttribute("libraries", libraryService.findAll());
        model.addAttribute("formLibrary", libraryService.findById(id));

        return "documents/libraries-list";
    }

    @PostMapping("/libraries/{id}")
    public String deleteLibrary(@PathVariable String id) {
        libraryService.deleteById(id);

        return "redirect:/document/libraries";
    }


    @PostMapping("/libraries")
    public String saveLibrary(@Valid @ModelAttribute("formLibrary") LibraryDto libraryDto,
                              BindingResult bindingResult,
                              Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("libraries", libraryService.findAll());
            model.addAttribute("formLibrary", libraryDto);

            return "documents/libraries-list";
        }

        Library library = new Library(libraryDto);
        libraryService.save(library);

        return "redirect:/document/libraries";
    }

    private static boolean thereAreErrorsIn(BindingResult theBindingResult, List<String> fieldsToIgnore) {
        if (!theBindingResult.hasErrors()) {
            return false;
        }

        for(FieldError theFieldError : theBindingResult.getFieldErrors()) {
            if(!fieldsToIgnore.contains(theFieldError.getField())) {
                return true;
            }
        }
        return false;
    }
}
