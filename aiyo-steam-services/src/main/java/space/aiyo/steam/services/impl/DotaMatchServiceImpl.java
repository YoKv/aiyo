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
import java.util.ArrayList;
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
     * 从steam获取数据，并入库
     *
     * @param matchSeqNum
     */
    @Override
    public void saveMatchFromSteamByMatchSeqNum(long matchSeqNum) {
        List<DotaMatchEntity> matches = getMatchFromSteamApiByMatchSeqNum(matchSeqNum);
        try {
            insertAll(matches); //200ms
//        saveAll(matches); //240ms
        } catch (Exception e) {
            logger.error("match数据库插入出错 " + e.getMessage());
        }
    }

    /**
     * 从steam 获取比赛信息
     * TODO 速度慢 8s
     *
     * @param matchSeqNum
     * @return
     */
    private List<DotaMatchEntity> getMatchFromSteamApiByMatchSeqNum(long matchSeqNum) {
        //从下一个开始,避免第一条与本地数据库最后一条重复
        matchSeqNum++;
        String returnStr = "";
        String url = SteamContsant.STEAM_API_PATH + SteamApiEnum.GET_MATCH_HISTORY_BY_SEQUENCE_NUM.getUrl() +
                "?start_at_match_seq_num=" +
                matchSeqNum +
                "&matches_requested=" +
                SteamContsant.STEAM_MATCH_PULLNUM_ONCE +
                "&key=" + SteamContsant.STEAM_KEY;
        try {
            returnStr = HttpUtil.sendGet(url);
        } catch (IOException e) {
            logger.info("调用steam接口失败,url:" + url + " *_* " + e.toString());
        }
        if (!returnStr.isEmpty()) {
            JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
            JSONArray matchArray = result.getJSONArray("matches");
            return JSON.parseArray(matchArray.toJSONString(), DotaMatchEntity.class);
        } else {
            return new ArrayList<>();
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
    public long maxSeqNum() {
//        long skip = repository.count() - 1;
//      DotaMatchEntity match = repository.findOne(Query.query(Criteria.where("match_seq_num").gt(DotaContsant.FIRST_MATCH_SEQ_NUM)).skip(skip).limit(1), DotaMatchEntity.class);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").gt(DotaContsant.FIRST_MATCH_SEQ_NUM));
        query.limit(1);
        DotaMatchEntity match = mongoTemplate.findOne(query, DotaMatchEntity.class);
        return match.getMatchSeqNum();
    }

    //获取最大的match_seq_num几种方案
    //skip n-1 并发可能有问题
    //sort limit
    //db.dotaMatch.find({},{_id:1}).sort({_id:-1}).limit(1)

    /**---------------------------------------------------------------数-------------------------------------------------------------**/
    /**---------------------------------------------------------------据-------------------------------------------------------------**/
    /**---------------------------------------------------------------库-------------------------------------------------------------**/
    /**---------------------------------------------------------------方-------------------------------------------------------------**/
    /**---------------------------------------------------------------法-------------------------------------------------------------**/
    /**---------------------------------------------------------------封-------------------------------------------------------------**/
    /**---------------------------------------------------------------装-------------------------------------------------------------**/

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
    private List<DotaMatchEntity> insertAll(List<DotaMatchEntity> matches) {
        //将id冗余，避免使用match_seq_num做id，破坏本身数据结构
        for (DotaMatchEntity match :
                matches) {
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
    private DotaMatchEntity save(DotaMatchEntity match) {
        match.setId(match.getMatchSeqNum());
        return repository.save(match);
    }

    /**
     * 批量保存
     *
     * @param matches
     * @return 插入的数据
     */
    private List<DotaMatchEntity> saveAll(List<DotaMatchEntity> matches) {
        //将id冗余，避免使用match_seq_num做id，破坏本身数据结构
        for (DotaMatchEntity match :
                matches) {
            match.setId(match.getMatchSeqNum());
        }
        return repository.saveAll(matches);
    }

    /**
     * 总数
     *
     * @return 总数
     */
    private int count() {
        long size = repository.count();
        int count = Integer.parseInt(String.valueOf(size));
        return count;
    }

    //TODO

    private void more() {
        repository.insert(new ArrayList<>());
        repository.insert(new DotaMatchEntity());
        repository.save(new DotaMatchEntity());
        repository.saveAll(new ArrayList<>());

//        repository.findAll(new Sort());
//        repository.findAll(Example);
//        repository.findAll(Example,Sort);
//        repository.findAll(Page);
//        repository.findAll(Example,Page);
//        repository.findAll(Page);
//        repository.findAllById(new ArrayList<>());
//        repository.count(Example.of());
//
//        repository.d


    }


}
