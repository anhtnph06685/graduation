package fpoly.graduation.project.client.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListCourseRequest {
    private FilterCourseRequest course;
    private Integer pageSize;
    private Integer pageNumber;
    private String orderBy;
}
