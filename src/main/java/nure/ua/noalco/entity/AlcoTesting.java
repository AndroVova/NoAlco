package nure.ua.noalco.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alco_testing")
public class AlcoTesting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "value", nullable = false)
    private double value;

    @Past(message = "The date must be in the past")
    @NonNull
    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @JsonIgnore
    @OneToMany(mappedBy = "alcoTesting", cascade = CascadeType.ALL)
    private List<Sensor> sensor;
}
