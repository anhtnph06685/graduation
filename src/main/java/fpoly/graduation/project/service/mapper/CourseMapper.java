package fpoly.graduation.project.service.mapper;

import fpoly.graduation.project.domain.Course;
import fpoly.graduation.project.service.dto.CourseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CourseMapper extends EntityMapper<CourseDTO, Course>{

}
