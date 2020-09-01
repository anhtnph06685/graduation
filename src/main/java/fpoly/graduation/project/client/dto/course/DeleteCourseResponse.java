package fpoly.graduation.project.client.dto.course;

import fpoly.graduation.project.service.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCourseResponse {
    private List<CourseDTO> courses;
}
