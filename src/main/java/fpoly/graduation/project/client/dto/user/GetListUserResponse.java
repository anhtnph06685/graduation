package fpoly.graduation.project.client.dto.user;

import com.fis.egp.common.domain.OptimizedPage;
import fpoly.graduation.project.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListUserResponse {
    OptimizedPage<UserDTO> page;
}
