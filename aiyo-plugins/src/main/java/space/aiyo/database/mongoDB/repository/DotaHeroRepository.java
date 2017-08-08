package space.aiyo.database.mongoDB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.database.mongoDB.entity.DotaHeroEntity;


/**
 * 英雄repository
 * Created by Yo on 2017/5/26.
 */
public interface DotaHeroRepository extends MongoRepository<DotaHeroEntity, String> {

}
