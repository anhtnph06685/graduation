package fpoly.graduation.project.client.dto.user;

import fpoly.graduation.project.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListUserRequest {
    private FilterUserRequest user;
    private Integer pageSize;
    private Integer pageNumber;
    private String orderBy;
}
