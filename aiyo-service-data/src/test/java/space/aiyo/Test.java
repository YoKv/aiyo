package space.aiyo;

import entity.dota.HeroEntity;
import entity.dota.MatchEntity;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.aiyo.repository.HeroRepository;
import space.aiyo.repository.MatchRepository;

/**
 * CREATE BY Yo ON 2018/1/19 23:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

  @Autowired
  private MatchRepository repository;
  @Autowired
  private HeroRepository heroRepository;


  @org.junit.Test
  public void contextLoads() {

    System.out.println("test");

    MatchEntity matchEntity = repository.findOne(new ObjectId("5a61f441ce6907266041dbc1"));
    System.out.println(matchEntity);

    List<HeroEntity> list = heroRepository.findAll();
    System.out.println(list.size());

    //FIXME mongo的entity注解没有
  }
}
