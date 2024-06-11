package david_seu.practicweb.dto;

import david_seu.practicweb.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParentDto {

    private Long id;
    private String name;
    private Long userId;
    private User user;
}
