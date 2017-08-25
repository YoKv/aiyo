package space.aiyo.base.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.base.entity.DotaMatchEntity;

/**
 * repository作为持久层
 * Created by Yo on 2017/5/26.
 */
//@NoRepositoryBean
public interface DotaMatchDao extends MongoRepository<DotaMatchEntity, ObjectId> {
}