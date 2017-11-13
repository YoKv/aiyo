package space.aiyo.core.services.impl;

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
import space.aiyo.core.dao.DotaMatchDao;
import entity.DotaMatchEntity;
import space.aiyo.val.contsant.DotaContsant;
import space.aiyo.val.contsant.SteamContsant;
import space.aiyo.val.enums.SteamApiEnum;
import space.aiyo.core.services.DotaMatchService;
import net.HttpUtil;

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
    final private DotaMatchDao matchDao;
    final private MongoTemplate mongoTemplate;

    @Autowired
    public DotaMatchServiceImpl(DotaMatchDao matchDao, MongoTemplate mongoTemplate) {
        this.matchDao = matchDao;
        this.mongoTemplate = mongoTemplate;
        InnerMethod.setMatchDao(matchDao);
    }

    /**
     * 从steam 获取比赛信息
     *
     * @param matchSeqNum 本地最大的matchSeqNum
     */
    @Override
    public void saveMatchFromSteamApiByMatchSeqNum(long matchSeqNum) {
        new Thread(() -> {
            List<DotaMatchEntity> matches = InnerMethod.getMatchFromSteamApiByMatchSeqNum(matchSeqNum);
            InnerMethod.saveAll(matches);
        }).start();

    }

    /**
     * 获取本地最大的sequenceNumber
     *
     * @return 本地最大的sequenceNumber
     */
    @Override
    public long getRecentSequenceNumber() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "_id"));
        query.limit(1);
        DotaMatchEntity match = mongoTemplate.findOne(query, DotaMatchEntity.class);
        if (null != match) {
            return match.getMatchSeqNum();
        } else {
            return 0;
        }
    }


    /**
     * 获取本地最大的sequenceNumber 暂时使用上面方法
     *
     * @return 最大的match_seq_num
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


    /**
     * 总数
     *
     * @return 总数
     */
    @Override
    public int count() {
        long size = matchDao.count();
        return Integer.parseInt(String.valueOf(size));
    }

    /**
     * 提供静态方法的内部静态类
     */
    private static class InnerMethod {
        private static Logger logger = LoggerFactory.getLogger(DotaMatchServiceImpl.InnerMethod.class);
        private static DotaMatchDao matchDao;

        /**
         * 根据matchSeqNum获取比赛信息
         *
         * @param matchSeqNum 比赛序列号
         * @return 比赛列表
         */
        private static List<DotaMatchEntity> getMatchFromSteamApiByMatchSeqNum(long matchSeqNum) {
            StringBuilder url = new StringBuilder();
            url.append(SteamContsant.STEAM_API_PATH).append(SteamApiEnum.GET_MATCH_HISTORY_BY_SEQUENCE_NUM.getUrl());
            //从下一个开始,避免第一条与本地数据库最后一条重复
            url.append("?start_at_match_seq_num=").append(matchSeqNum + 1);
            url.append("&matches_requested=").append(SteamContsant.STEAM_MATCH_PULLNUM_ONCE);
            url.append("&key=").append(SteamContsant.STEAM_KEY);
            try {
                String returnStr = HttpUtil.sendGet(url.toString());
                if (!StringUtils.isEmpty(returnStr)) {
                    JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
                    JSONArray matchArray = result.getJSONArray("matches");
                    return JSON.parseArray(matchArray.toJSONString(), DotaMatchEntity.class);
                }
            } catch (IOException e) {
                logger.info("调用steam接口保存失败,url:" + url + " *_* " + e.toString());
            }
            return new ArrayList<>();
        }

        /**
         * 单条插入
         *
         * @param match 比赛实体
         * @return 插入数据库后的记录
         */
        private static DotaMatchEntity insert(DotaMatchEntity match) {
            return matchDao.insert(match);
        }

        /**
         * 批量插入
         *
         * @param matches 比赛实体列表
         * @return 插入的数据
         */
        private static List<DotaMatchEntity> insertAll(List<DotaMatchEntity> matches) {
            return matchDao.insert(matches);
        }

        /**
         * 保存
         * 无记录insert
         * 有记录update
         *
         * @param match 比赛实体
         * @return 插入数据库后的记录
         */
        private static DotaMatchEntity save(DotaMatchEntity match) {
            return matchDao.save(match);
        }

        /**
         * 批量保存
         *
         * @param matches 比赛实体列表
         * @return 插入的数据
         */
        private static List<DotaMatchEntity> saveAll(List<DotaMatchEntity> matches) {
            return matchDao.saveAll(matches);
        }

        private static void setMatchDao(DotaMatchDao matchDao) {
            InnerMethod.matchDao = matchDao;
        }
    }


}
