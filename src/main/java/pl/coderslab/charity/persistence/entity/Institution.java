package pl.coderslab.charity.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter

@Entity
@Table(name = "institutions")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.REMOVE)
    private List<Donation> donation = new ArrayList<>();

}
