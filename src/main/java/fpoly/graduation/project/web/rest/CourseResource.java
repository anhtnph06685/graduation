package fpoly.graduation.project.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import fpoly.graduation.project.client.dto.course.*;
import fpoly.graduation.project.domain.Course;
import fpoly.graduation.project.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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

    @PostMapping("/save")
    public ResponseEntity<BaseDataResponse<CreateCourseResponse>> create(
            @RequestBody BaseDataRequest<CreateCourseRequest> request
    ){
        try {
            CreateCourseResponse response = courseService.createCourse(request.getBody());
            return ResponseUtil.wrap(response);
        }
        catch (ServiceException e){
            return ResponseUtil.generateErrorResponse(e);
        }
        catch (Exception e){
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<BaseDataResponse<UpdateCourseResponse>> update(
            @RequestBody BaseDataRequest<UpdateCourseRequest> request
    ){
        try {
            UpdateCourseResponse response = courseService.update(request.getBody());
            return ResponseUtil.wrap(response);
        }
        catch (ServiceException e){
            return ResponseUtil.generateErrorResponse(e);
        }
        catch (Exception e){
            return ResponseUtil.generateErrorResponse(e);
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<BaseDataResponse<DeleteCourseResponse>> delete(
            @RequestBody BaseDataRequest<DeleteCourseRequest> request
    ){
        try {
            DeleteCourseResponse response = courseService.delete(request.getBody());
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
