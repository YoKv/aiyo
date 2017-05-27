package space.aiyo.steam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.steam.entity.Dota2MatchEntity;


/**
 * Created by Yo on 2017/5/26.
 */
public interface Dota2MatchRepository extends MongoRepository<Dota2MatchEntity, String> {

}
