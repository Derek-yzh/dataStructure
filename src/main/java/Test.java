import java.util.HashSet;
import java.util.Random;

/**
 * @Author: Derek
 * @DateTime: 2020/12/17 21:58
 * @Description: TODO
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();
        while (set.size() != 5){
            int i = random.nextInt(100);
            set.add(i);
        }

        System.out.println(set);
    }

}






