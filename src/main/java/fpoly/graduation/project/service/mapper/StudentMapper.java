package fpoly.graduation.project.service.mapper;

import fpoly.graduation.project.domain.Student;
import fpoly.graduation.project.service.dto.StudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface StudentMapper extends EntityMapper<StudentDTO, Student>{


    default Student fromId(Integer id ){
        if(id == null){
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
