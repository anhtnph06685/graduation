package fpoly.graduation.project.client.dto.student;

import fpoly.graduation.project.service.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentResponse {
    private StudentDTO student;
}
