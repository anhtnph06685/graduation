package fpoly.graduation.project.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private Integer status;

    private Instant createdDate;

    private String createdBy;

    private Instant lastModifiedDate;

    private String lastModifiedBy;

}
