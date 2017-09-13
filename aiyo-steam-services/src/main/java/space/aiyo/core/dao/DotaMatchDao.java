package space.aiyo.core.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.core.entity.DotaMatchEntity;

/**
 * repository作为持久层
 * Created by Yo on 2017/5/26.
 */
//@NoRepositoryBean
public interface DotaMatchDao extends MongoRepository<DotaMatchEntity, ObjectId> {
}