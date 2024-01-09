package ie.atu.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/getBook/{title}")
    Book getBookFromRepo(@PathVariable String title) {
        Book book = new Book();
        if (bookKeeperService.getLogged() != 1) {
            book.setBookTitle("Not logged in");
        } else {
            book = bookServiceClient.getBook(title);
        }
        return book;
    }
}
