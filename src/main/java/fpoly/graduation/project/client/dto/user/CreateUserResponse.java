package fpoly.graduation.project.client.dto.user;

import fpoly.graduation.project.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {
    private UserDTO user;
}
