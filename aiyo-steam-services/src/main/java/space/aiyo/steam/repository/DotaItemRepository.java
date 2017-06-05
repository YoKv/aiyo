package space.aiyo.steam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.steam.entity.DotaItemEntity;


/**
 * 游戏装备repository
 * Created by Yo on 2017/5/26.
 */
public interface DotaItemRepository extends MongoRepository<DotaItemEntity, String> {

}
