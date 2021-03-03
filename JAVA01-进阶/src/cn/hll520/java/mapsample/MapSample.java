package cn.hll520.java.mapsample;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-03-14:56
 * @since 2021-03-03-14:56
 */
public class MapSample {
    @Test
    public void keySet(){
        Map map=new HashMap();
        map.put("language","java");
        map.put(486,"re0");
        map.put(null,"ok");
        map.put("name","lpc");
        Iterator keys = map.keySet().iterator();
        while (keys.hasNext())
            System.out.println(keys.next());
        Iterator values = map.values().iterator();
        while (values.hasNext())
            System.out.println(values.next());

        Iterator nodes = map.entrySet().iterator();
        while (nodes.hasNext()) {
            Map.Entry next = (Map.Entry) nodes.next();
            System.out.println(next);
            System.out.print(next.getKey());
            System.out.print("="+next.getValue()+"\n");
            System.out.println("__________");
            
        }


    }
}
