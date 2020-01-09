package luoma.test_cms.Service.Impl;

import luoma.test_cms.Dao.HeroDao;
import luoma.test_cms.Entity.Hero;
import luoma.test_cms.Service.HeroService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("heroService")
public class HeroServicelmpl implements HeroService {
    @Resource
    private HeroDao heroDao;

    @Override
    public List<Hero> getAllHeroInfo() {
        List<Hero> stuInfo =this.heroDao.getAllHeroInfo();
        return stuInfo;
    }

    @Override
    public List<Hero> getStudentInfoById(int id) {
        List<Hero> stuInfo =this.heroDao.getHeroInfoById(id);
        return stuInfo;
    }

    @Override
    public List<Hero> getStudentInfoByName(String name) {
        List<Hero> stuInfo =this.heroDao.getHeroInfoByName(name);
        return stuInfo;
    }

    @Override
    public int updateHeroInfo(Hero heroInfo) {
        return this.heroDao.updateHeroInfo(heroInfo);
    }


    @Override
    public int getMaxHeroId() {
        return this.heroDao.getMaxHeroId();
    }

    @Override
    public int addHeroInfo(Hero heroInfo) {
        return this.heroDao.addHeroInfo(heroInfo);
    }

    @Override
    public int deleteHeroInfo(Integer id) {
        return this.heroDao.deleteHeroInfo(id);
    }
}
