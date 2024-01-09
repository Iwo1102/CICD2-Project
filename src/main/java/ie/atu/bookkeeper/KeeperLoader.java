package ie.atu.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KeeperLoader implements CommandLineRunner {

    private final BookKeeperRepo bookKeeperRepo;

    @Autowired
    public KeeperLoader(BookKeeperRepo bookKeeperRepo) {
        this.bookKeeperRepo = bookKeeperRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        BookKeeper keeper1 = new BookKeeper(1L, "Admin1", "admin123", "adminMain@keeper.com");

        bookKeeperRepo.save(keeper1);
    }
}
