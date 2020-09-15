package fpoly.graduation.project.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.Constants;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.domain.OptimizedPage;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import fpoly.graduation.project.client.dto.user.*;
import fpoly.graduation.project.domain.User;
import fpoly.graduation.project.repository.UserRepository;
import fpoly.graduation.project.service.dto.UserDTO;
import fpoly.graduation.project.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {
        ServiceException.class,
        Exception.class
})
public class UserService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(
            UserRepository userRepository,
            UserMapper userMapper
    ){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Transactional(readOnly = true)
    public GetListUserResponse loadListUser(GetListUserRequest request) throws ServiceException, Exception{
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getUser() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("user", ValidationError.NotNull))
                        .build();
            }
            if(request.getPageNumber() < 0){
                request.setPageNumber(1);
            }
            if(request.getPageSize() < 0){
                request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
            }
            Pageable pageable = PageRequest.of(request.getPageNumber() - 1,
                    request.getPageSize(),
                    Sort.by(Sort.Direction.ASC, StringUtils.isEmpty(request.getOrderBy()) ? "id" : request.getOrderBy()));

            FilterUserRequest  searchParams = request.getUser();

            Page<UserDTO> page = userRepository.getListUser(pageable,
                    searchParams.getUsername() !=null  ? searchParams.getUsername() : null
                    ).map(userMapper :: toDto);


            GetListUserResponse response = new GetListUserResponse();
            response.setPage(OptimizedPage.convert(page));

            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    public CreateUserResponse createUser(CreateUserRequest request) throws ServiceException, Exception{
        try {
            if(request == null ){
                ServiceUtil.generateEmptyPayloadError();
            }
            if (request.getUser() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("user",ValidationError.NotNull))
                        .build();
            }
            User user = new User();
            user.setUsername(request.getUser().getUsername());
            user.setPassword(request.getUser().getPassword());
            user.setStatus(request.getUser().getStatus());
            user.setCreatedDate(Instant.now());
            user.setCreatedBy("admin");
            user.setLastModifiedDate(Instant.now());
            user.setCreatedBy("admin");

            CreateUserResponse response = new CreateUserResponse();

            userRepository.save(user);
            response.setUser(userMapper.toDto(user));

            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw  e;
        }
    }

    public UpdateUserResponse updateUser(UpdateUserRequest request) throws ServiceException, Exception{
        try {
            if(request == null ){
                ServiceUtil.generateEmptyPayloadError();
            }
            if (request.getUser() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("user",ValidationError.NotNull))
                        .build();
            }
            Optional<User> userOptional = userRepository.findById(request.getUser().getId());
            if(!userOptional.isPresent()){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("user",ValidationError.NotNull))
                        .build();
            }

            User user = new User();
            user.setId(request.getUser().getId());
            user.setUsername(request.getUser().getUsername());
            user.setPassword(request.getUser().getPassword());
            user.setStatus(request.getUser().getStatus());
            user.setCreatedDate(Instant.now());
            user.setCreatedBy("admin");
            user.setLastModifiedDate(Instant.now());
            user.setCreatedBy("admin");

            UpdateUserResponse response = new UpdateUserResponse();

            userRepository.save(user);
            response.setUser(userMapper.toDto(user));

            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw  e;
        }
    }
    public DeleteUserResponse  delete(DeleteUserRequest request) throws ServiceException, Exception{
        try {
            if(request == null ){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getId() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("id", ValidationError.NotNull))
                        .build();
            }
            if(request.getId() < 1){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("id", ValidationError.NegativeOrZero))
                        .build();
            }
            userRepository.deleteById(request.getId());

            List<User> list = userRepository.findAll();
            DeleteUserResponse response = new DeleteUserResponse();
            response.setUser(userMapper.toDto(list));

            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
}
