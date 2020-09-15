package fpoly.graduation.project.repository;

import fpoly.graduation.project.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("from User u where 1=1 " +
            "and (u.username like concat('%',:username,'%') or :username is null )"
    )
    Page<User> getListUser(Pageable pageable,
                           @Param("username") String  username
                           );
}
