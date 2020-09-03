package greedy;

import java.util.*;

public class Medium {
    /**
     * 不重叠的区间个数
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int end = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }
        return intervals.length - count;
    }

    /**
     * 用最少的箭射爆所有气球：类似与上面的那个，这个是求重叠区间个数
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        //这种写法排序比用lambda快
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                continue;
            }
            count++;
            end = points[i][1];
        }
        return count;
    }

    /**
     * 根据身高和序号重新排队
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return new int[0][];
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });
        ArrayList<int[]> arrayList = new ArrayList<>();
        for (int[] p: people) {
            arrayList.add(p[1], p);
        }
        return arrayList.toArray(new int[arrayList.size()][]);
    }

    /**
     * 划分字母区间
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        char[] chars = S.toCharArray();
        int[] count = new int[26];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            count[c-'a']++;
        }
        List<Integer> res = new ArrayList<>();
        int[] pCount = new int[26];
        int s = -1;
        Set<Character> set = new HashSet<>();
        int rem = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            set.add(c);
            pCount[c-'a']++;
            if (pCount[c-'a'] == count[c-'a']) {
                rem++;
                if (set.size() == rem) {
                    res.add(i-s);
                    s = i;
                    set.clear();
                    rem = 0;
                }
            }
        }
        return res;
    }
    /**
     * 上面方法自己想的，虽然也挺快的，但是略显的蠢笨
     * 这个方法也太厉害了
     * @param S
     * @return
     */
    public List<Integer> partitionLabels2(String S) {
        List<Integer> res = new ArrayList<>();
        int[] last = new int[26]; //每个字符在S中最后一次出现的位置
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            last[chars[i]-'a'] = i;
        }
        int maxLast = 0, start = -1;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            maxLast = Math.max(maxLast, last[c-'a']);
            if (i == maxLast) {
                res.add(i - start);
                start = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Medium medium = new Medium();
        System.out.println(medium.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
