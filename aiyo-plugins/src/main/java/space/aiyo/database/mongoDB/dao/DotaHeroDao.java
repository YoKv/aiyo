package space.aiyo.database.mongoDB.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.database.mongoDB.entity.DotaHeroEntity;

/**
 * repository作为持久层
 * Created by tang on 2017/8/11.
 */
public interface DotaHeroDao extends MongoRepository<DotaHeroEntity, String> {

}
