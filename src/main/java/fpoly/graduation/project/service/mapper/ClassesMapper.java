package fpoly.graduation.project.service.mapper;

import fpoly.graduation.project.domain.Classes;
import fpoly.graduation.project.domain.Course;
import fpoly.graduation.project.service.dto.ClassesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {})
public interface ClassesMapper extends EntityMapper<ClassesDTO, Classes>{

    @Mapping(source = "course.id",target = "courseId")
    ClassesDTO toDto(Classes classes);

    @Mapping(source = "courseId",target = "course.id")
    Classes toEntity(ClassesDTO classesDTO);

    default Classes fromId(Integer id){
        if(id == null){
            return null;
        }
        Classes classes = new Classes();
        classes.setId(id);
        return classes;
    }
}
