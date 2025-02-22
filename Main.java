/* 对下面数据的第二列数据，排序去重计数


输入:

1   aa
2   bb
3   cc
4   cc
5   dd

输出：

aa:1
bb:1
cc:2
dd:1 */
import java.*;
public class Main {

    public static void main(String[] args) {
        String[] s = {"aa","bb","cc","cc","dd"};
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i < s.length; i++) {
            if(map.get(s[i]) == null) {
                map.set(s[i], 1);
            }else {
                map.set(s[i], map.get(s[i])+ 1);
            }
        }

        // 排序


    }
}
