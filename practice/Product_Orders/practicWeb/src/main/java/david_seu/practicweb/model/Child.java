package david_seu.practicweb.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "orders")
public class Child {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String user;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Parent parent;
}
