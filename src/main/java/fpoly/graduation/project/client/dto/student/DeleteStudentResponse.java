package fpoly.graduation.project.client.dto.student;

import fpoly.graduation.project.service.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteStudentResponse {
    private List<StudentDTO> students;
}
