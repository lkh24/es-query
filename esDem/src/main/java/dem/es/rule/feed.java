package dem.es.rule;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：Lkh
 * @date ：Created in 2023/9/27 9:38 PM
 */
@CrossOrigin(origins = "http://localhost:8848", allowCredentials = "true")
@RestController
@RequestMapping("/feed")
public class feed {
    private static int count = 0;
    private static long time = System.currentTimeMillis();
    private static long time2 = System.currentTimeMillis();

    /**
     * 直接写一个方法用被需要请求的接口调用来记录请求次数并且每在一秒内请求十次就用INFO报一次日志并且打印时间和请求次数
     */
    public static void count() {
        count++;
        if (System.currentTimeMillis() - time < 1000) {
            if (count % 5 == 0) {
                System.out.println("time:" + (System.currentTimeMillis() - time2) + "ms" + " count:" + count);
            }
        } else {
            time = System.currentTimeMillis();
            time2 = System.currentTimeMillis();
        }
    }

    /**
     * 测试一个请求是否可以被请求到
     */
    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    /**
     * 这里写一个String[]用来模拟数据库存储信息海量公司名
     */
    private final static String[] company = {"阿里巴巴",
            "腾讯", "百度", "京东", "美团", "字节跳动", "华为", "小米", "拼多多", "滴滴", "网易", "携程",
            "360", "新浪", "快手", "微博", "苏宁", "爱奇艺", "哔哩哔哩", "搜狐", "贝壳", "58同城", "虎牙", "腾讯蘑菇街",
            "去哪儿", "唯品会", "途家网", "阿里饿了么", "大众点评", "美团点评", "58赶集", "赶集网", "搜房网", "腾讯链家网",
            "安居客", "房天", "房多多", "链家", "阿里贝壳找房", "安居客", "房多多", "房天下", "我爱我家", "安居客", "腾讯我爱我家"};

    /**
     * 根据传入的关键字查询公司名
     *
     * @return
     */
    @PostMapping("/getList")
    public List<String> feed(@RequestBody Map<String, String> body) {
        count();
        String con = body.get("keyword");
        ArrayList<String> result = new ArrayList<>();
        if (StringUtils.isEmpty(con)) {
            return result;
        }
//        进行匹配填充result
        for (String s : company) {
            if (s.contains(con)) {
                result.add(s);
            }
        }
        return result;
    }
}
