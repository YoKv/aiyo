package space.aiyo.steam.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.steam.entity.DotaHeroEntity;

/**
 * repository作为持久层
 * Created by tang on 2017/8/11.
 */
public interface DotaHeroDao extends MongoRepository<DotaHeroEntity, String> {
    DotaHeroEntity findByIdOrNameOrLocalizedName(int id, String name, String localizedName);
}
