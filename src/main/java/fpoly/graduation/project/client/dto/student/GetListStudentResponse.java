package fpoly.graduation.project.client.dto.student;

import com.fis.egp.common.domain.OptimizedPage;
import fpoly.graduation.project.service.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListStudentResponse {
    private OptimizedPage<StudentDTO> page;
}
