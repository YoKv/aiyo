package space.aiyo.steam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.steam.entity.Dota2ItemEntity;


/**
 * Created by Yo on 2017/5/26.
 */
public interface Dota2ItemRepository extends MongoRepository<Dota2ItemEntity, String>{

}
