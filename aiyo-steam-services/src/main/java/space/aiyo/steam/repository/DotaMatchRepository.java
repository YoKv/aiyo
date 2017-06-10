package space.aiyo.steam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.steam.entity.match.DotaMatchEntity;


/**
 * 比赛repository
 * Created by Yo on 2017/5/26.
 */
public interface DotaMatchRepository extends MongoRepository<DotaMatchEntity, String> {

}
