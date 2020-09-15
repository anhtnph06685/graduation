package fpoly.graduation.project.client.dto.user;

import fpoly.graduation.project.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserResponse {
    private List<UserDTO> user;
}
