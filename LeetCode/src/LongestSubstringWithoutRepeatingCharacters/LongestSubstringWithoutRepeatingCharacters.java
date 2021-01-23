package LongestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Example 4:
 * Input: s = ""
 * Output: 0
 *
 * Constraints:
 *      1. 0 <= s.length <= 5 * 104
 *      2. s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /*
    该方法时间复杂度太高，对于大量数据无法实现
     */
    public int lengthOfLongestSubstringME(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        //循环 substring 的长度
        for (int i = len - 1; i > 0; i--) {
            label: for (int j = 0; j < len - i; j++) {
                String substring = s.substring(j, j + i + 1);
                //检查 substring 是否含有相同元素
                for (int k = 0; k < i; k++) {
                    char charAtK = substring.charAt(k);
                    for (int n = k + 1; n < i + 1; n++) {
                        if (charAtK == substring.charAt(n)) {
                            //没通过检查，换下一个 substring 检查
                            continue label;
                        }
                    }
                }
                //通过检查返回
                return i + 1;
            }
        }
        return 1;
    }

    /*
    Brute Force：
        > 暴力求解
        > 时间复杂度 O(n^3)
        > 空间复杂度 O(min(m, n)) m 是 charset 的大小
     */
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }
    //验证 substring 的独特性，从 start 开始，不包括 end
    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    /*
    Sliding Window：
        > 时间复杂度 O(n)
        > 空间复杂度 O(min(m, n))
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /*
    Sliding Window Optimized：
        > 时间复杂度 O(n)
        > 空间复杂度 O(min(m, n)) (HashTable)
        > 空间复杂度 O(m) (Table)
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            // 该方法会不断更新每一个 character 的 value 值到最大的位置，因为与当前 character 相等的最大 value 位置之前都不需要再检查了
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /*
    此方法使用 ASCII 128：
        Commonly used tables are:
            > int[26] for Letters 'a' - 'z' or 'A' - 'Z'
            > int[128] for ASCII
            > int[256] for Extended ASCII
     */
    public int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            //如果未包含，那么 index[s.charAt(j)] = 0
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
