package space.aiyo.repository;

import entity.dota.HeroEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CREATE BY Yo ON 2018/1/20 00:07
 */
public interface HeroRepository extends MongoRepository<HeroEntity, ObjectId> {

}
