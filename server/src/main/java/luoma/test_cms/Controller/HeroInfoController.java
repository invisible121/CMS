package luoma.test_cms.Controller;

import luoma.test_cms.Entity.Hero;
import luoma.test_cms.Service.HeroService;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hero")
public class HeroInfoController {

    @Resource
    private HeroService heroSerive;

    @RequestMapping(value = "/selectAllHero")
    public List<Hero> selectAllHero(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域问题
        List<Hero> allHeroInfo = heroSerive.getAllHeroInfo();
        return allHeroInfo;
    }

    @RequestMapping(value = "/selectHeroById")
    public List<Hero> selectHeroById(HttpServletRequest request, HttpServletResponse response, String heroId) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Hero> HeroInfo = heroSerive.getStudentInfoById(Integer.parseInt(heroId));
        return HeroInfo;
    }

    @RequestMapping(value = "/selectHeroByName")
    public List<Hero> selectHeroByName(HttpServletRequest request, HttpServletResponse response, String heroName) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Hero> HeroInfo = new ArrayList<Hero>();
        if (heroName.equals("")) {

        } else {
            HeroInfo = heroSerive.getStudentInfoByName(heroName);
        }
        return HeroInfo;
    }

    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request, HttpServletResponse response, String test) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (test.equals("1"))
        return "hhh";
        else return "lalala";
    }

}

//    @RequestMapping(value="updateHeroById")
//    public JSONObject updateStudentInfo(HttpServletRequest request, HttpServletResponse response, String hero) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        JSONObject jsonobject = JSONObject.fromObject(hero);
//        Hero rule = (Hero) JSONObject.toBean(jsonobject, Hero.class);
//        int result = heroSerive.updateHeroInfo(rule);
//        JSONObject state = new JSONObject();
//        if(result ==1){
//            state.put("state", "修改成功");
//        }
//        else{
//            state.put("state", "修改失败");
//        }
//        return state;
//    }
//    @RequestMapping(value="/addHeroInfo")
//    public List<Hero> addStudentInfo(HttpServletRequest request, HttpServletResponse response, String name){
//        response.setHeader("Access-Control-Allow-Origin", "*");
////        JSONObject jsonobject = JSONObject.fromObject(json);
//        //获取英雄的最大ID
//        int heroMaxId = heroSerive.getMaxHeroId();
//        int heroId = heroMaxId+1;
//
//        JSONObject heroInfo = new JSONObject();
//        heroInfo.put("id", heroId);
//        heroInfo.put("name", name);
//
//        Hero rule = (Hero) JSONObject.toBean(heroInfo, Hero.class);
//        heroSerive.addHeroInfo(rule);
//        List<Hero> HeroInfo=heroSerive.getStudentInfoById(heroId);
//        return HeroInfo;
//
//
//    }
//    @RequestMapping(value="deleteHeroById")
//    public JSONObject deleteHeroById(HttpServletRequest request, HttpServletResponse response, String  id) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        int result = heroSerive.deleteHeroInfo(Integer.parseInt(id));
//
//        JSONObject state = new JSONObject();
//        if (result == 1) {
//            state.put("state", "删除成功");
//        }
//    }