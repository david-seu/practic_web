package david_seu.practicweb.mapper;

import david_seu.practicweb.dto.ParentDto;
import david_seu.practicweb.model.Parent;

public class ParentMapper {

    public static Parent toEntity(ParentDto parentDto) {
        return new Parent(parentDto.getId(), parentDto.getName());
    }

    public static ParentDto toDto(Parent parent) {
        return new ParentDto(parent.getId(), parent.getName());
    }
}
