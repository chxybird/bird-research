package com.bird.controller;

import com.bird.entity.Card;
import com.bird.entity.User;
import com.bird.utils.DocReportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author lipu
 * @Date 2021/4/11 12:18
 * @Description
 */
@RestController
@RequestMapping("/freemarker")
@Slf4j
public class FreemarkerController {

    /**
     * @Author lipu
     * @Date 2021/4/11 12:19
     * @Description freemarker的数据类型调研
     */
    @GetMapping("/dataType")
    public ModelAndView dataType(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("data-type");
        //布尔类型
        Boolean flag=true;
        modelAndView.addObject("flag",flag);
        //日期类型
        Date date=new Date();
        modelAndView.addObject("date",date);
        //数值类型
        Double num=0.6;
        modelAndView.addObject("num",num);
        //字符串类型
        String str="http://127.0.0.1:9658/student/findAll";
        modelAndView.addObject("str",str);
        //空值类型处理
        modelAndView.addObject("e",null);
        //集合类型 数组、list、set
        List<String> stringList=new ArrayList<>();
        stringList.add("张三");
        stringList.add("李四");
        modelAndView.addObject("stringList",stringList);
        //哈希类型
        Map<String,Object> map=new HashMap<>();
        map.put("id",16036024);
        map.put("name","小鸟程序员");
        map.put("age",24);
        modelAndView.addObject("map",map);
        return modelAndView;
    }

    /**
     * @Author lipu
     * @Date 2021/4/11 18:28
     * @Description freemarker的常用指令调研
     */
    @GetMapping("/instruct")
    public ModelAndView instruct(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("instruct");
        int[] arrayList={1,2,3,4,5,6,7,8,9};
        //list指令测试
        modelAndView.addObject("arrayList",arrayList);
        //if elseif else 指令测试
        Integer flag=1;
        modelAndView.addObject("flag",flag);
        return modelAndView;
    }

    /**
     * @Author lipu
     * @Date 2021/4/12 11:35
     * @Description freemarker页面静态化调研
     */
    @GetMapping("/view")
    public void view(){

    }

    /**
     * @Author lipu
     * @Date 2021/4/13 11:29
     * @Description freemarker+DocReport文件下载调研
     */
    @PostMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        //获取类路径下的word模板文件
        ClassPathResource pathResource=new ClassPathResource("bird.docx");
        File file = pathResource.getFile();
        DocReportUtils.init(file);
        //设置数据
        User user=new User(16036024L,"小鸟",24);
        DocReportUtils.set("user",user);
        List<Card> cardList=new ArrayList<>();
        cardList.add(new Card(1L,"招商银行卡"));
        cardList.add(new Card(2L,"建设银行卡"));
        cardList.add(new Card(3L,"邮政银行卡"));
        cardList.add(new Card(4L,"农业银行卡"));
        DocReportUtils.setList("cardList",cardList);
        //下载
        DocReportUtils.download(response);
    }
}
