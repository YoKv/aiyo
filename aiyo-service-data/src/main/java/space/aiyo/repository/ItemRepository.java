package space.aiyo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.entity.ItemRepositoryEntity;

/**
 * CREATE BY Yo ON 2018/1/20 00:10
 */
public interface ItemRepository extends MongoRepository<ItemRepositoryEntity, ObjectId> {

}
