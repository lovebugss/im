import java.util.*;

/**
 * 测试for 循环
 *
 * @author renjp
 * @date 2022/1/17 08:15
 */
public class ForEachTest {
    public static void main(String[] args) {
        ForEachTest forEachTest = new ForEachTest();

        Map<String, String> data = forEachTest.getData();
        Map<String, String> data1 = forEachTest.getData();
        long start = System.currentTimeMillis();
        Set<String> strings = forEachTest.test1(data);
        long end1 = System.currentTimeMillis();
        Set<String> strings1 = forEachTest.test2(data1);
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - end1);
        System.out.println(end1 - start);
        System.out.println(strings.size());
        System.out.println(strings1.size());
    }

    public Set<String> test1(Map<String, String> data) {
        HashSet<String> objects = new HashSet<>();
        Set<Map.Entry<String, String>> entries = data.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            if (key.contains("0")) {
                objects.add(key);
            }
        }
        for (String object : objects) {
            data.remove(object);
        }
        return objects;
    }

    public Set<String> test2(Map<String, String> data) {
        HashSet<String> objects = new HashSet<>();
        Iterator<Map.Entry<String, String>> iterator = data.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            if (key.contains("0")) {
                objects.add(key);
                iterator.remove();
            }
        }
        return objects;
    }

    public Map<String, String> getData() {
        Map<String, String> objects = new LinkedHashMap<>();

        for (int i = 0; i < 10000; i++) {
            objects.put("i" + i, UUID.randomUUID().toString());
        }

        return objects;
    }
}
