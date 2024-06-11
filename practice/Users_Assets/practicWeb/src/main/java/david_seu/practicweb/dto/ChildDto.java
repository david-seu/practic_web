package david_seu.practicweb.dto;

import david_seu.practicweb.model.Parent;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ChildDto {

    private Long id;
    private String name;

    private Long parentId;

    private Parent parent;

    private String description;

    private int value;

}
