package HashMapCustom;

public class Main {


    public static void main(String[] args) {
        MapCustom<Integer, Integer> map = new MapCustom<>();
        map.put(1, 123123);
        map.put(2, 86887);
        map.put(3, 432423);
        map.put(4, 155555);
        map.put(5, 5454);
        System.out.println(map.get(3));
        System.out.println(map.containsKey(4));
        System.out.println(map.containsValue(5454));
        System.out.println(map.containsValue(54334));
        System.out.println(map.containsKey(5454));
        System.out.println(map.size());
        map.remove(2);
        System.out.println(map.size());

    }
}
