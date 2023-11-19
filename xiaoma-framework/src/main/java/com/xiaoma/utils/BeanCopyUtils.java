package com.xiaoma.utils;

import com.xiaoma.domain.entity.Article;
import com.xiaoma.vo.HotArticleVo;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sound.midi.spi.SoundbankReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    public static <S, T> T copyBean(S source, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <S, T> List<T> copyBeanList(List<S> source, Class<T> clazz){

        return source.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());

    }

//    测试用例
//    public static void main(String[] args) {
//       System.out.println(1 =='1');
//       Character c = '0';
//       Integer i = 0;
//       Boolean b = c.equals(i);
//       if (b){
//            System.out.printf("yes");
//        } else {
//           System.out.printf("不等价");
//       }
//    }
    final private String password = "123456";

    @Test
    public void TestCrypt(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode1 = bCryptPasswordEncoder.encode(password);
        System.out.println("encode1: " + encode1);
        String encode2 = bCryptPasswordEncoder.encode(password);
        System.out.println("encode2: " + encode2);

    }
}
