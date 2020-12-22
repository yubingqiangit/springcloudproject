package com.yu.test;/**
 *
 * @author yubingqian
 * @date 2020-10-10 10:19
 *
 */

import com.yu.model.ModelStakeRel;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/10/10 10:19 
 */
public class StreamTest {
    public static void main(String[] args) {
       /* List<ModelStakeRel> list = new ArrayList<>();
        ModelStakeRel m = new ModelStakeRel();
       // m.setStakeNo("1111");
        m.setModelId("1000000000");
        list.add(m);
        ModelStakeRel m1 = new ModelStakeRel();
        m1.setStakeNo("2222222");
        m1.setModelId("2000000000");
        list.add(m1);
        Map<String, String> map = list.stream().collect(Collectors.toMap(ModelStakeRel::getStakeNo,ModelStakeRel::getModelId));
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> stringStringEntry : entrySet) {
            System.err.println(stringStringEntry.getKey() + "|" + stringStringEntry.getValue());
        }*/
        List<String> list = new ArrayList<>();
        try {
            list = get(list);
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       list.stream().forEach(e->{
           System.out.println(e);
       });
    }

    public static List<String> get(List<String> list) {
        list.add("adddd");

        return list;
    }
}
