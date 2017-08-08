package space.aiyo.database.mongoDB.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.database.mongoDB.entity.match.DotaMatchEntity;


/**
 * 比赛repository
 * Created by Yo on 2017/5/26.
 */
public interface DotaMatchRepository extends MongoRepository<DotaMatchEntity, ObjectId> {

}
