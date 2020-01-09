package luoma.test_cms.Dao;

import luoma.test_cms.Entity.Hero;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HeroDao {
    List<Hero> getAllHeroInfo();
    //获取所有英雄信息
    List<Hero> getHeroInfoById(Integer id);
    //获取查询英雄信息
    List<Hero> getHeroInfoByName(String name);

    //修改英雄信息
    int updateHeroInfo(Hero heroInfo);
    //获取现有英雄的最大ID
    int getMaxHeroId();
    //添加英雄信息
    int addHeroInfo(Hero hero);

    //根据学号删除英雄信息
    int deleteHeroInfo(Integer id);

}
