package ie.atu.bookkeeper;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @NotBlank(message = "Title Cannot be blank")
    private String title;

    @NotBlank(message = "Author Cannot be blank")
    private String author;

    @NotNull(message = "year cannot be blank")
    private int year;

    @Min(value = 0, message = "Amount cannot be less than 0")
    private int amount;

    @NotNull(message = "Age Range must be be entered") //test
    private int ageRange;
}
