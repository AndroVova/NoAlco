package nure.ua.noalco.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private double maxValue;

    @ManyToOne
    @JoinColumn(name = "alco_testing_id", referencedColumnName = "id", nullable = true)
    private AlcoTesting alcoTesting;
}
