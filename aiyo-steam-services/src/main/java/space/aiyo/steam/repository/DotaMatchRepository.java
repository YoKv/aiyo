package space.aiyo.steam.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.aiyo.steam.entity.match.DotaMatchEntity;


/**
 * 比赛repository
 * Created by Yo on 2017/5/26.
 */
public interface DotaMatchRepository extends MongoRepository<DotaMatchEntity, ObjectId> {
//    @Query("find({},{_id:1}).sort({_id:-1}).limit(1)")
//    DotaMatchEntity maxSeq();


}
