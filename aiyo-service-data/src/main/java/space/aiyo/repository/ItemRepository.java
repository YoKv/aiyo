package space.aiyo.repository;

import entity.dota.ItemEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CREATE BY Yo ON 2018/1/20 00:10
 */
public interface ItemRepository extends MongoRepository<ItemEntity, ObjectId> {

}
