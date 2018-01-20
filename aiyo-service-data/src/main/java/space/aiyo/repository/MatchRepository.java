package space.aiyo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.entity.MatchRepositoryEntity;

/**
 * CREATE BY Yo ON 2018/1/20 00:09
 */
public interface MatchRepository extends MongoRepository<MatchRepositoryEntity, ObjectId> {

}
