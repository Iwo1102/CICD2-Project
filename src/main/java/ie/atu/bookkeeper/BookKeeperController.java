package ie.atu.bookkeeper;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookKeeperController {

    private final BookKeeperService bookKeeperService;
    private final BookServiceClient bookServiceClient;

    @Autowired
    public BookKeeperController(BookKeeperService bookKeeperService, BookServiceClient bookServiceClient) {
        this.bookKeeperService = bookKeeperService;
        this.bookServiceClient = bookServiceClient;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, String password) {
        if(bookKeeperService.getLogged() == 1) {
            return ResponseEntity.badRequest().body("Already Logged in");
        }
        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Missing username or password");
        }
        BookKeeper user = bookKeeperService.getBookKeeperByUsername(username);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        if (password.equals(user.getPassword())) {
            bookKeeperService.setLogged(1);
            return ResponseEntity.ok("Logged in Successfully as " + username);
        } else {
            return ResponseEntity.badRequest().body("Incorrect password");
        }

    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        if(bookKeeperService.getLogged() == 0) {
            return ResponseEntity.badRequest().body("Already logged out");
        }
        bookKeeperService.setLogged(0);
        return ResponseEntity.ok("Logged out Successfully");
    }

    @GetMapping("/getBook/{title}")
    Book getBook(@PathVariable String title) {
        Book book = new Book();
        if (bookKeeperService.getLogged() != 1) {
            book.setBookTitle("Not logged in");
        } else {
            book = bookServiceClient.getBook(title);
        }
        return book;
    }

    @PostMapping("/addBook")
    ResponseEntity<?> saveBook(@Valid @RequestBody Book book) {
        if (bookKeeperService.getLogged() != 1) {
            return ResponseEntity.badRequest().body("Not logged in");
        }
        bookServiceClient.addBookToRepo(book);
        return ResponseEntity.ok("Book Added");
    }


    @DeleteMapping ("/removeBook/{id}")
    ResponseEntity<?> removeBook(@PathVariable Long id) {
        if (bookKeeperService.getLogged() != 1) {
            return ResponseEntity.badRequest().body("Not logged in");
        }
        bookServiceClient.deleteBookFromRepo(id);
        return ResponseEntity.ok("Book " + id + " Removed");
    }

    @PutMapping("/editBook/{title}")
    ResponseEntity<?> editBook(@PathVariable String title, @RequestBody Book book) {
        if (bookKeeperService.getLogged() != 1) {
            return ResponseEntity.badRequest().body("Not logged in");
        }
        bookServiceClient.editBookInRepo(title, book);
        return ResponseEntity.ok("Book Edited");
    }
}
