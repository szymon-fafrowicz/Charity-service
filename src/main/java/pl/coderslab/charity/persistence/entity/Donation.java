package pl.coderslab.charity.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter

@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = Integer.MAX_VALUE)
    private Integer quantity;

    @ManyToMany
    @NotEmpty
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "institution_id")
    @NotNull
    private Institution institution;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    @Future
    private LocalDate pickUpDate;

    @NotNull
    private LocalTime pickUpTime;

    @Length(max = 200)
    private String pickUpComment;
}
