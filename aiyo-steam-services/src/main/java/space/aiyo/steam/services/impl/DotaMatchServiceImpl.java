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
import space.aiyo.steam.contsant.DotaContsant;
import space.aiyo.steam.contsant.SteamContsant;
import space.aiyo.steam.entity.match.DotaMatchEntity;
import space.aiyo.steam.enums.SteamApiEnum;
import space.aiyo.steam.repository.DotaMatchRepository;
import space.aiyo.steam.services.DotaMatchService;
import space.aiyo.steam.util.HttpUtil;


import java.io.IOException;
import java.util.List;

/**
 * Dota比赛接口实现类
 * Created by yo on 2017/5/27.
 */

@Service
public class DotaMatchServiceImpl implements DotaMatchService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DotaMatchRepository repository;

    @Autowired
    public DotaMatchServiceImpl(DotaMatchRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查出全部记录
     *
     * @return
     */
    public List<DotaMatchEntity> findAll() {
        return repository.findAll();
    }

    public int count() {
        long size = repository.count();
        int count = Integer.parseInt(String.valueOf(size));
        return count;
    }

    @Override
    public void saveMatch(DotaMatchEntity match) {
        try {
            //将id冗余，避免使用match_seq_num做id，破坏本身数据结构
            match.setId(match.getMatchSeqNum());
            repository.save(match);
        } catch (Exception e) {
            logger.info("id" + match.getId() + "游戏比赛数据存入数据库失败: " + e.toString());
        }
    }

    /**
     * 从steam 获取比赛信息
     *
     * @param matchSeqNum
     * @return
     */
    private List<DotaMatchEntity> getMatchFromSteamApi(long matchSeqNum) {
        String returnStr = "";
        String url = SteamContsant.STEAM_API_PATH + SteamApiEnum.GetMatchHistoryBySequenceNum.getUrl() +
                "?start_at_match_seq_num=" +
                matchSeqNum +
                "&matches_requested=" +
                SteamContsant.STEAM_MATCH_PULLNUM_ONCE +
                "&key=" + SteamContsant.STEAM_KEY;
        try {
            returnStr = HttpUtil.sendGet(url);
        } catch (IOException e) {
            logger.info("调用steam接口失败,url:" + url + e.toString());
        }
        JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
        JSONArray matchArray = result.getJSONArray("matches");

        return JSON.parseArray(matchArray.toJSONString(), DotaMatchEntity.class);
    }

    /**
     * 保存 TODO 批量
     *
     * @param matchSeqNum
     * @return
     */
    @Override
    public void saveMatchFromSteamApiBySequenceNumber(long matchSeqNum) {
        List<DotaMatchEntity> matches = getMatchFromSteamApi(matchSeqNum);
        for (DotaMatchEntity match :
                matches) {
            saveMatch(match);
        }
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
        System.out.println(match);
        if (null != match) {
            return match.getMatchSeqNum();
        } else {
            return 0;
        }

    }

    /**
     * 再找最优的方案
     *
     * @return
     */
    @Override
    public long maxSeqNum() {
//        long skip = repository.count() - 1;
//      DotaMatchEntity match = repository.findOne(Query.query(Criteria.where("match_seq_num").gt(DotaContsant.FIRST_MATCH_SEQ_NUM)).skip(skip).limit(1), DotaMatchEntity.class);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").gt(DotaContsant.FIRST_MATCH_SEQ_NUM));
        query.limit(1);
        DotaMatchEntity match = mongoTemplate.findOne(query, DotaMatchEntity.class);
        System.out.println(match);
        return match.getMatchSeqNum();
    }

    //获取最大的match_seq_num几种方案
    //skip n-1 并发可能有问题
    //sort limit
    //db.dotaMatch.find({},{_id:1}).sort({_id:-1}).limit(1)
}
