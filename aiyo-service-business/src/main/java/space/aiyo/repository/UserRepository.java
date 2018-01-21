package space.aiyo.repository;

import org.springframework.data.repository.CrudRepository;
import space.aiyo.entity.UserRepositoryEntity;

/**
 * CREATE BY Yo ON 2018/1/21 12:08
 */
public interface UserRepository extends CrudRepository<UserRepositoryEntity, Long> {

}
