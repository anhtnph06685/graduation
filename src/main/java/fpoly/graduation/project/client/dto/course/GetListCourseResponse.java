package fpoly.graduation.project.client.dto.course;

import com.fis.egp.common.domain.OptimizedPage;
import fpoly.graduation.project.service.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListCourseResponse {
    OptimizedPage<CourseDTO> page;
}
