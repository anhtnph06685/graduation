package fpoly.graduation.project.client.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListStudentRequest {
    private FilterStudentRequest student;
    private Integer pageNumber;
    private Integer pageSize;
    private String orderBy;
}
