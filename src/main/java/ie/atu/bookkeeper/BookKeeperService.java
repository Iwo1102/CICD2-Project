package ie.atu.bookkeeper;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class BookKeeperService {
    private final BookKeeperRepo bookKeeperRepo;

    private int logged = 0;

    public BookKeeperService(BookKeeperRepo bookKeeperRepo) {
        this.bookKeeperRepo = bookKeeperRepo;
    }

    public BookKeeper getBookKeeperByUsername(String username) {

        return bookKeeperRepo.findBookKeeperByUsername(username);
    }

    public void saveKeeper(BookKeeper bookKeeper) {
        this.bookKeeperRepo.save(bookKeeper);
    }
}
