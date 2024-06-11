package david_seu.practicweb.mapper;

import david_seu.practicweb.dto.ChildDto;
import david_seu.practicweb.model.Child;

public class ChildMapper {

    public static Child toEntity(ChildDto childDto) {
        return new Child(childDto.getId(), childDto.getName(), childDto.getDescription(), childDto.getValue(), childDto.getParent());
    }

    public static ChildDto toDto(Child child) {
        return new ChildDto(child.getId(), child.getName(), child.getParent().getId(), child.getParent(), child.getDescription(), child.getValue());
    }
}
