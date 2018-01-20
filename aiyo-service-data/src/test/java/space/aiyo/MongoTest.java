package space.aiyo;

import entity.dota.MatchEntity;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.aiyo.entity.HeroRepositoryEntity;
import space.aiyo.repository.HeroRepository;
import space.aiyo.repository.MatchRepository;

/**
 * CREATE BY Yo ON 2018/1/19 23:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

  @Autowired
  private MatchRepository repository;
  @Autowired
  private HeroRepository heroRepository;


  @Test
  public void contextLoads() {
    MatchEntity matchEntity = repository.findById(new ObjectId("5a61f441ce6907266041dbc1")).get();
    System.out.println(matchEntity);

    List<HeroRepositoryEntity> list = heroRepository.findAll();
    System.out.println(list.size());
    System.out.println(list.get(1));

  }
}
