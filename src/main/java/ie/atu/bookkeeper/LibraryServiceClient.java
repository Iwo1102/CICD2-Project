package ie.atu.bookkeeper;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Library-service", url = "http://localhost:8081")
public interface LibraryServiceClient {
}
