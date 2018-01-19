package space.aiyo.repository;

import entity.dota.MatchEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CREATE BY Yo ON 2018/1/20 00:09
 */
public interface MatchRepository extends MongoRepository<MatchEntity, ObjectId> {

}
