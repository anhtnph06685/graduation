package fpoly.graduation.project.client.dto.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListClassRequest {
    private FilterClassRequest classes;
    private Integer pageSize;
    private Integer pageNumber;
    private String orderBy;
}
