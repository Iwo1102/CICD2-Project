package ie.atu.bookkeeper;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Book-service", url = "http://localhost:8081")
public interface BookServiceClient {

    @GetMapping("/getBook/{title}")
    Book getBook(@PathVariable String title);
}
