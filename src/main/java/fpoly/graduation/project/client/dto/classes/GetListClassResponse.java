package fpoly.graduation.project.client.dto.classes;

import com.fis.egp.common.domain.OptimizedPage;
import fpoly.graduation.project.service.dto.ClassesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListClassResponse {
    OptimizedPage<ClassesDTO> page;
}
