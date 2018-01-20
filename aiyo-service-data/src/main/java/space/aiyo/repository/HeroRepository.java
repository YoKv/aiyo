package space.aiyo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.entity.HeroRepositoryEntity;

/**
 * CREATE BY Yo ON 2018/1/20 00:07
 */
public interface HeroRepository extends MongoRepository<HeroRepositoryEntity, ObjectId> {

}
