package fpoly.graduation.project.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import fpoly.graduation.project.client.dto.course.GetListCourseRequest;
import fpoly.graduation.project.client.dto.course.GetListCourseResponse;
import fpoly.graduation.project.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CourseService courseService;

    public CourseResource(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping("/get-list")
    public ResponseEntity<BaseDataResponse<GetListCourseResponse>> getListCourse(
            @RequestBody BaseDataRequest<GetListCourseRequest> request
            ){
        try {
            GetListCourseResponse response = courseService.getListCourse(request.getBody());
            return ResponseUtil.wrap(response);
        }
        catch (ServiceException e){
            return ResponseUtil.generateErrorResponse(e);
        }
        catch (Exception e){
            return ResponseUtil.generateErrorResponse(e);
        }
    }
}
