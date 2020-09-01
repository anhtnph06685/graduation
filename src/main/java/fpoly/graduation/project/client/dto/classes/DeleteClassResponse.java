package fpoly.graduation.project.client.dto.classes;

import fpoly.graduation.project.service.dto.ClassesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteClassResponse {
    private List<ClassesDTO> classes;
}
