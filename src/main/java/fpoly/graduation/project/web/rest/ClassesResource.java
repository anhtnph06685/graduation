package fpoly.graduation.project.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import fpoly.graduation.project.client.dto.classes.*;
import fpoly.graduation.project.client.dto.course.DeleteCourseRequest;
import fpoly.graduation.project.client.dto.course.DeleteCourseResponse;
import fpoly.graduation.project.domain.Classes;
import fpoly.graduation.project.service.ClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/classes")
public class ClassesResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ClassesService classesService;

    public ClassesResource(
            ClassesService classesService
    ){
        this.classesService = classesService;
    }

    @PostMapping("/get-list")
    public ResponseEntity<BaseDataResponse<GetListClassResponse>> loadListClasses(
            @RequestBody BaseDataRequest<GetListClassRequest> request
            ){
        try {
            GetListClassResponse response = classesService.getListClasses(request.getBody());
            return ResponseUtil.wrap(response);
        }
        catch (ServiceException e){
            return ResponseUtil.generateErrorResponse(e);
        }
        catch (Exception e){
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/add-class")
    public  ResponseEntity<BaseDataResponse<CreateClassResponse>> createClass(
            @RequestBody BaseDataRequest<CreateClassRequest> request
    ){
        try {
            CreateClassResponse response = classesService.createClass(request.getBody());
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
    public ResponseEntity<BaseDataResponse<UpdateClassResponse>> update(
            @RequestBody BaseDataRequest<UpdateClassRequest> request
    ){
        try {
            UpdateClassResponse response = classesService.update(request.getBody());
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
    public ResponseEntity<BaseDataResponse<DeleteClassResponse>> delete(
            @RequestBody BaseDataRequest<DeleteClassRequest> request
    ){
        try {
            DeleteClassResponse response = classesService.delete(request.getBody());
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
