package space.aiyo.database.mongoDB.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.database.mongoDB.entity.match.DotaMatchEntity;

/**
 * repository作为持久层
 * Created by Yo on 2017/5/26.
 */
public interface DotaMatchDao extends MongoRepository<DotaMatchEntity, ObjectId> {

}