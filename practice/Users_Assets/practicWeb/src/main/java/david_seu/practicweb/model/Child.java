package david_seu.practicweb.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "assets")
public class Child {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int value;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private Parent parent;


}
