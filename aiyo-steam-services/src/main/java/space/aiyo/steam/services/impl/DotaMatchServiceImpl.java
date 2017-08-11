package space.aiyo.steam.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import space.aiyo.database.mongoDB.dao.DotaMatchDao;
import space.aiyo.database.mongoDB.entity.match.DotaMatchEntity;
import space.aiyo.steam.contsant.DotaContsant;
import space.aiyo.steam.contsant.SteamContsant;
import space.aiyo.steam.enums.SteamApiEnum;
import space.aiyo.steam.services.DotaMatchService;
import space.aiyo.util.HttpUtil;

import java.io.IOException;
import java.util.List;

/**
 * Dota比赛接口实现类
 * Created by yo on 2017/5/27.
 */

@Service
public class DotaMatchServiceImpl implements DotaMatchService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    final private DotaMatchDao matchDao;

    public DotaMatchServiceImpl(DotaMatchDao matchDao) {
        this.matchDao = matchDao;
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 从steam获取数据，并入库
     *
     * @param matchSeqNum
     */
    @Override
    public void saveMatchFromSteamByMatchSeqNum(long matchSeqNum) {
//        List<DotaMatchEntity> matches = getMatchFromSteamApiByMatchSeqNum(matchSeqNum);
//        try {
//            insertAll(matches); //200ms
////        saveAll(matches); //240ms
//        } catch (Exception e) {
//            logger.error("match数据库插入出错 " + e.getMessage());
//        }
    }

    /**
     * 从steam 获取比赛信息
     * TODO 速度慢 8s
     *
     * @param matchSeqNum
     * @return
     */
    @Override
    public void getMatchFromSteamApiByMatchSeqNum(long matchSeqNum) {
        //从下一个开始,避免第一条与本地数据库最后一条重复
        matchSeqNum++;
        String url = SteamContsant.STEAM_API_PATH + SteamApiEnum.GET_MATCH_HISTORY_BY_SEQUENCE_NUM.getUrl() + "?start_at_match_seq_num=" + matchSeqNum + "&matches_requested=" + SteamContsant.STEAM_MATCH_PULLNUM_ONCE + "&key=" + SteamContsant.STEAM_KEY;

//        try {
//            String returnStr = HttpUtil.sendGet(url);
//            JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
//            JSONArray matchArray = result.getJSONArray("matches");
//            List<DotaMatchEntity> matches = JSON.parseArray(matchArray.toJSONString(), DotaMatchEntity.class);
//            insertAll(matches); //200ms
//        } catch (IOException e) {
//            logger.info("调用steam接口保存失败,url:" + url + " *_* " + e.toString());
//        }
        new Thread(() -> {
            try {
                String returnStr = HttpUtil.sendGet(url);
                if (!StringUtils.isEmpty(returnStr)) {
                    JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
                    JSONArray matchArray = result.getJSONArray("matches");
                    List<DotaMatchEntity> matches = JSON.parseArray(matchArray.toJSONString(), DotaMatchEntity.class);
                    matchDao.saveAll(matches);
                }
            } catch (IOException e) {
                logger.info("调用steam接口保存失败,url:" + url + " *_* " + e.toString());
            }
        }).start();

    }

    @Override
    public void recursion(long sequenceNumber) {
        if (sequenceNumber == 0) {
            sequenceNumber = DotaContsant.FIRST_MATCH_SEQ_NUM;//从7.00版本开始
        }
        getMatchFromSteamApiByMatchSeqNum(sequenceNumber);
        sequenceNumber = getRecentSequenceNumber();
        recursion(sequenceNumber);
    }

    /**
     * 获取本地最大的sequenceNumber
     *
     * @return
     */
    @Override
    public long getRecentSequenceNumber() {
        Query query = new Query();
        query.with(new Sort(Sort.Order.desc("_id")));
        query.limit(1);
        DotaMatchEntity match = mongoTemplate.findOne(query, DotaMatchEntity.class);
        if (null != match) {
            return match.getMatchSeqNum();
        } else {
            return 0;
        }

    }

    /**
     * 单条插入
     *
     * @param match
     * @return 插入数据库后的记录
     */
    public DotaMatchEntity insert(DotaMatchEntity match) {
        match.setId(match.getMatchSeqNum());
        return matchDao.insert(match);
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
        return matchDao.insert(matches);
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
        return matchDao.save(match);
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
        return matchDao.saveAll(matches);
    }

    /**
     * 总数
     *
     * @return 总数
     */
    public int count() {
        long size = matchDao.count();
        int count = Integer.parseInt(String.valueOf(size));
        return count;
    }

    /**
     * 再找最优的方案
     *
     * @return
     */
    public long maxSeqNum() {
//        long skip = repository.count() - 1;
//      DotaMatchEntity match = repository.findOne(Query.query(Criteria.where("match_seq_num").gt(DotaContsant.FIRST_MATCH_SEQ_NUM)).skip(skip).limit(1), DotaMatchEntity.class);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").gt(DotaContsant.FIRST_MATCH_SEQ_NUM));
        query.limit(1);
        DotaMatchEntity match = mongoTemplate.findOne(query, DotaMatchEntity.class);
        return match.getMatchSeqNum();
    }


}
