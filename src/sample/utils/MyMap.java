package sample.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author guokun
 * @date 2024/8/17 21:11
 */
public class MyMap<V> extends LinkedHashMap<String,V> {
   public V indexOf(int i) {
       if (i < 0 || i >= size()) {
           return null;
       }
       int index = 0;
       for (V value : values()) {
           if (index++ == i) {
               return value;
           }
       }
       return null;
   }

   public List<V> getListByIdSet(Set<String> ids) {
       return this.entrySet().stream().filter(e -> ids.contains(e.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
   }
}
