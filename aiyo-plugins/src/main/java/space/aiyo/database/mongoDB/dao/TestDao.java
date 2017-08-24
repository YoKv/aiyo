package space.aiyo.database.mongoDB.dao;

import org.springframework.data.repository.RepositoryDefinition;
import space.aiyo.database.mongoDB.entity.DotaHeroEntity;

/**
 * Created by tang on 2017/8/24.
 */
@RepositoryDefinition(domainClass = DotaHeroEntity.class, idClass = Long.class)
public interface TestDao {
    //some menthods
}
