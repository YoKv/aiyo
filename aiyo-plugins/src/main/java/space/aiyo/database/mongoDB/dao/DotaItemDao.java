package space.aiyo.database.mongoDB.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.aiyo.database.mongoDB.entity.DotaItemEntity;

/**
 * repository作为持久层
 * Created by tang on 2017/8/11.
 */
public interface DotaItemDao extends MongoRepository<DotaItemEntity, String> {
    /**
     * 通过id查找一个装备
     *
     * @param id
     * @return
     */
    DotaItemEntity findDotaItemEntityById(int id);

    @Query("{ 'localized_name' : ?0 }")
    DotaItemEntity findByZHName(String localized_name);

    DotaItemEntity findByNameLike(String name);

}
