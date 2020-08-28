package fpoly.graduation.project.client.dto.classes;

import fpoly.graduation.project.service.dto.ClassesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClassRequest {
    private ClassesDTO classes;
}
