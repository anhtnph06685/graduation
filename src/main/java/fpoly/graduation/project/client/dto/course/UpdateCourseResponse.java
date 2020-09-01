package fpoly.graduation.project.client.dto.course;

import fpoly.graduation.project.service.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseResponse {
    private CourseDTO course;
}
