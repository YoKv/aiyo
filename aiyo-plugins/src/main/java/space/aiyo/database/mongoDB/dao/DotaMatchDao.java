package space.aiyo.database.mongoDB.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import space.aiyo.database.mongoDB.entity.match.DotaMatchEntity;

import java.util.List;

public class DotaMatchDao {
    @Autowired
    private DotaMatchRepository repository;

    public DotaMatchDao() {
    }

    /**
     * 单条插入
     *
     * @param match
     * @return 插入数据库后的记录
     */
    public DotaMatchEntity insert(DotaMatchEntity match) {
        match.setId(match.getMatchSeqNum());
        return repository.insert(match);
    }

    /**
     * 批量插入
     *
     * @param matches
     * @return 插入的数据
     */
    public List<DotaMatchEntity> insertAll(List<DotaMatchEntity> matches) {
        //将id冗余，避免使用match_seq_num做id，破坏本身数据结构
        for (DotaMatchEntity match : matches) {
            match.setId(match.getMatchSeqNum());
        }
        return repository.insert(matches);
    }

    /**
     * 保存
     * 无记录insert
     * 有记录update
     *
     * @param match
     * @return 插入数据库后的记录
     */
    public DotaMatchEntity save(DotaMatchEntity match) {
        match.setId(match.getMatchSeqNum());
        return repository.save(match);
    }


    /**
     * 批量保存
     *
     * @param matches
     * @return 插入的数据
     */
    public List<DotaMatchEntity> saveAll(List<DotaMatchEntity> matches) {
        //将id冗余，避免使用match_seq_num做id，破坏本身数据结构
        for (DotaMatchEntity match : matches) {
            match.setId(match.getMatchSeqNum());
        }
        return repository.saveAll(matches);
    }

    /**
     * 总数
     *
     * @return 总数
     */
    public int count() {
        long size = repository.count();
        int count = Integer.parseInt(String.valueOf(size));
        return count;
    }

}

/**
 * 比赛repository
 * Created by Yo on 2017/5/26.
 */
interface DotaMatchRepository extends MongoRepository<DotaMatchEntity, ObjectId> {

}