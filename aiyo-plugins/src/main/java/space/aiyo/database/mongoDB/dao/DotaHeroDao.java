package space.aiyo.database.mongoDB.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import space.aiyo.database.mongoDB.entity.DotaHeroEntity;

import java.util.List;

/**
 * hero持久层
 * Created by tang on 2017/8/10.
 */

public class DotaHeroDao {
    @Autowired
    private DotaHeroRepository repository;

    public DotaHeroDao() {
    }

    public List<DotaHeroEntity> findAll() {
        return repository.findAll();
    }

    public List<DotaHeroEntity> saveAll(List<DotaHeroEntity> lists) {
        return repository.saveAll(lists);
    }

}

interface DotaHeroRepository extends MongoRepository<DotaHeroEntity, String> {
}