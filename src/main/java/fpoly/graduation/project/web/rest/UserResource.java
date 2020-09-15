package fpoly.graduation.project.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import fpoly.graduation.project.client.dto.user.*;
import fpoly.graduation.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    public UserResource(
            UserService userService
    ){
        this.userService = userService;
    }

    @PostMapping("/get-list")
    public ResponseEntity<BaseDataResponse<GetListUserResponse>> loadListUser(
            @RequestBody BaseDataRequest<GetListUserRequest> request
            ){
        try {
            GetListUserResponse response = userService.loadListUser(request.getBody());
            return ResponseUtil.wrap(response);
        }
        catch (ServiceException e){
            return ResponseUtil.generateErrorResponse(e);
        }
        catch (Exception e){
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<BaseDataResponse<CreateUserResponse>> createUser(
            @RequestBody BaseDataRequest<CreateUserRequest> request
    ){
        try {
            CreateUserResponse response = userService.createUser(request.getBody());
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
    public ResponseEntity<BaseDataResponse<UpdateUserResponse>> updateUser(
            @RequestBody BaseDataRequest<UpdateUserRequest> request
    ){
        try {
            UpdateUserResponse response = userService.updateUser(request.getBody());
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
    public ResponseEntity<BaseDataResponse<DeleteUserResponse>> delete(
            @RequestBody BaseDataRequest<DeleteUserRequest> request
    ){
        try {
            DeleteUserResponse response = userService.delete(request.getBody());
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
