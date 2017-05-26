package space.aiyo.steam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.steam.entity.Dota2HeroEntity;


/**
 * Created by Yo on 2017/5/26.
 */
public interface Dota2HeroRepository extends MongoRepository<Dota2HeroEntity, String>{

}
