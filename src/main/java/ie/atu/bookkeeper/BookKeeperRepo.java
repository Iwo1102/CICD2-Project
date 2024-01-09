package ie.atu.bookkeeper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookKeeperRepo extends JpaRepository<BookKeeper, Long> {

    BookKeeper findBookKeeperByUsername(String username);
}
