package space.aiyo.database.mongoDB.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.aiyo.database.mongoDB.entity.DotaItemEntity;

import java.util.List;

/**
 * Created by tang on 2017/8/10.
 */
public class DotaItemDao {
    @Autowired
    private DotaItemRepository repository;

    public DotaItemDao() {
    }

    public List<DotaItemEntity> saveAll(List<DotaItemEntity> items) {
        return repository.saveAll(items);
    }

    public DotaItemEntity findDotaItemEntityById(int id) {
        return repository.findDotaItemEntityById(id);
    }

}

/**
 * 游戏装备repository
 * Created by Yo on 2017/5/26.
 */
interface DotaItemRepository extends MongoRepository<DotaItemEntity, String> {

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
