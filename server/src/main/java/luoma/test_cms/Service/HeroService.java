package luoma.test_cms.Service;

import luoma.test_cms.Entity.Hero;

import java.util.List;

public interface HeroService {
    //获取所有hero
    public List<Hero> getAllHeroInfo();
    //根据id获取hero，返回List
    public List<Hero> getStudentInfoById(int id);
    //根据name获取hero，返回List
    public List<Hero> getStudentInfoByName(String name);
    //修改Hero信息
    int updateHeroInfo(Hero heroInfo);
    //获取hero的最大Id
    int getMaxHeroId();
    //添加Hero信息
    int addHeroInfo(Hero heroInfo);
    //根据id删除Hero信息
    int deleteHeroInfo(Integer id);

}
