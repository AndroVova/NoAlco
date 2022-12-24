package nure.ua.noalco.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @Past(message = "The birth date must be in the past")
    @NonNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "Position cannot be blank")
    @NonNull
    @Column(name = "position", nullable = false)
    private String position;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "place_of_residence", nullable = false)
    private String placeOfResidence;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = true)
    private Department department;

    @JsonIgnore
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Profile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<AlcoTesting> alcoTestings;

}
