package david_seu.practicweb.dto;

import david_seu.practicweb.model.Parent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChildDto {

    private Long id;
    private String username;

    private String father;

    private String mother;

}
