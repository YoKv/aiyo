package space.aiyo.database.mongoDB.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import space.aiyo.database.mongoDB.entity.DotaHeroEntity;

import java.util.List;

/**
 * Created by tang on 2017/8/10.
 */
@Component
public class DotaHeroDao {

    final private DotaHeroRepository repository;

    @Autowired
    public DotaHeroDao(DotaHeroRepository repository) {
        this.repository = repository;
    }

    private interface DotaHeroRepository extends MongoRepository<DotaHeroEntity, String> {
        DotaHeroEntity findDistinctFirstById(String id);
    }

    public List<DotaHeroEntity> findAll(){
      
    }

}
