package space.aiyo.steam.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

    //批量插入 TODO
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

//    /**
//     * 分页查询
//     *
//     * @param page
//     * @param rows
//     * @return
//     */
//    public Page<DotaMatchEntity> findByPages(int page, int rows) {
//        PageRequest request = new PageRequest(page - 1, rows);
//        return repository.findAll(request);
//    }

    public int count() {
        long size = repository.count();
        int count = Integer.valueOf(String.valueOf(size));
        return count;
    }


    @Override
    public void saveMatch(DotaMatchEntity match) {
        try {
            //将id冗余，避免使用match_seq_num做id，破坏本身数据结构
            match.setId(match.getMatch_seq_num());
            repository.save(match);
        } catch (Exception e) {
            logger.info("id" + match.getId() + "游戏比赛数据存入数据库失败: " + e.toString());
        }
    }

    /**
     * 一次100条 后期通过admin配置 TODO
     *
     * @param sequenceNumber
     * @return
     */
    private List<DotaMatchEntity> getMatchFromSteamApi(long sequenceNumber) {
        String returnStr = "";
        String url = SteamContsant.STEAM_API_PATH + SteamApiEnum.GetMatchHistoryBySequenceNum.getUrl()
                + "?start_at_match_seq_num=" + sequenceNumber + "&matches_requested=100&key=" + SteamContsant.STEAM_KEY;
        try {
            returnStr = HttpUtil.sendGet(url);
        } catch (IOException e) {
            logger.info("调用steam接口失败: " + e.toString());
        }
        JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
        JSONArray matchArray = result.getJSONArray("matches");

        return JSON.parseArray(matchArray.toJSONString(), DotaMatchEntity.class);
    }

    /**
     * @param sequenceNumber
     * @return
     */
    @Override
    public void saveMatchFromSteamApiBySequenceNumber(long sequenceNumber) {
        List<DotaMatchEntity> matches = getMatchFromSteamApi(sequenceNumber);
        for (DotaMatchEntity match :
                matches) {
            saveMatch(match);
        }
        System.out.println(System.currentTimeMillis());
    }

    @Override
    public long getRecentSequenceNumber() {
        Query query = new Query();
        query.with(new Sort(Sort.Order.desc("_id")));
        query.limit(1);
        DotaMatchEntity match = mongoTemplate.findOne(query, DotaMatchEntity.class);
        System.out.println(match);

        return match.getMatch_seq_num();
    }

    @Override
    public long maxSeqNum() {
        long skip = repository.count() - 1;
//      DotaMatchEntity match = repository.findOne(Query.query(Criteria.where("match_seq_num").gt(DotaContsant.FIRST_MATCH_SEQ_NUM)).skip(skip).limit(1), DotaMatchEntity.class);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").gt(DotaContsant.FIRST_MATCH_SEQ_NUM));
        query.limit(1);
        DotaMatchEntity match = mongoTemplate.findOne(query, DotaMatchEntity.class);
        System.out.println(match);
        return match.getMatch_seq_num();
    }

    //获取最大的match_seq_num几种方案
    //skip n-1 并发可能有问题
    //sort limit
    //db.dotaMatch.find({},{_id:1}).sort({_id:-1}).limit(1)
}
