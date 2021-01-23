package LongestPalindromicSubstring;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Example 3:
 * Input: s = "a"
 * Output: "a"
 *
 * Example 4:
 * Input: s = "ac"
 * Output: "a"
 *
 * Constraints:
 *      1. 1 <= s.length <= 1000
 *      2. s consist of only digits and English letters (lower-case and/or upper-case),
 */
public class LongestPalindromicSubstring {

    /*
    逻辑有问题！！！对于全都是一样的字符，不能处理，如：ccccc

    解题思路：
        1. 利用 sliding window 寻找两头相等的子串
        2. 如果符合要求就左右扩张
        3. 左边如果扩张到 0 就停止
        4. 一旦停止扩张，将左边设置为右边的位置，右边位置加一
        5. 一直到右边位置到达 s.length 后程序停止
        6. 每一次成功扩张或者每一次确定两头相等的子串就计算其长度，如果长度大于所存储的最大长度，就将对应左右的位置存储起来
        7. 长度为： 右 - 左 + 1
    注意：
        1. 第一个两头相等的子串可以是 bab 也可以是 bb 形式
        2. 所以第一个子串需要单独判断，当相邻两个 char 不相等，则左边按兵不动，右边加一即可，如此一来 bab 也考虑进来了
     */
    public String longestPalindromeME1(String s) {
        int currLeft = 0;
        int currRight = 1;
        int left = 0;
        int right = 0;
        while(currRight < s.length()) {
            //寻找第一个子串需要单独判断
            if (currRight - currLeft + 1 <= 2) {
                if (s.charAt(currLeft) == s.charAt(currRight)) {
                    if ((currRight - currLeft + 1) > (left - right + 1)) {
                        left = currLeft;
                        right = currRight;
                    }
                    if (currLeft == 0) {
                        currLeft = currRight++;
                    } else {
                        currLeft--;
                        currRight++;
                    }
                } else {
                    currRight++;
                }
            } else if (currRight - currLeft + 1 == 3) {
                //已经有找到子串后，正好是三个一组，如果不满足，左边也应该放到中间那一个 char 判断，而右边保持不动
                if (s.charAt(currLeft) == s.charAt(currRight)) {
                    if ((currRight - currLeft + 1) > (left - right + 1)) {
                        left = currLeft;
                        right = currRight;
                    }
                    if (currLeft == 0) {
                        currLeft = currRight++;
                    } else {
                        currLeft--;
                        currRight++;
                    }
                } else {
                    currLeft = currRight - 1;
                }
            } else {
                //已经有找到长度大于等于 3 子串后，可以如此判断
                if (s.charAt(currLeft) == s.charAt(currRight)) {
                    if ((currRight - currLeft + 1) > (left - right + 1)) {
                        left = currLeft;
                        right = currRight;
                    }
                    if (currLeft == 0) {
                        currLeft = currRight++;
                    } else {
                        currLeft--;
                        currRight++;
                    }
                } else {
                    currLeft = currRight++;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    /*
    解题思路 1：暴力求解
        1. 一个函数 boolean checkPalindromic(String substring) 来检查 substring 是否符合 palindromic 要求
        2. 遍历所有的 substring 如果符合要求，长度最大的返回
    时间复杂度：O(n^3)

    解题思路 2：Sliding Window
        1. 两种大的查询方向：
            1) 所查询的 substring 中所有字符相等，allSame = true
            2) 所查询的 substring 中并非所有字符相等，allSame = false
        2. 一旦 currRight - currLeft + 1 = 2，则需要设置 allSame = true
        3. allSame = true：
            1) 始终检查 currRight 和 currRight-1 项，如果相等则 currRight++
            2) 如果不相等：
                > 如果 currRight - currLeft + 1 <= 2：
                    > currRight++，下次跳转 allSame = false 查询
                > 否则：
                    > 如果 currLeft != 0, 左侧有位置，currLeft--，下次跳转 allSame = false 查询
                    > 否则，currLeft = currRight - 1
        4. allSame = false：
            1) 始终检查 currLeft 和 currRight 项
            2) 如果相等：
                > 如果 currLeft != 0，左侧有位置，currLeft-- currRight++
                > 如果 currLeft = 0，左侧无位置，currLeft = (currLeft + currRight) / 2，并且设置 allSame = true
            3) 如果不等：
                > 如果 currRight - currLeft + 1 <= 2，则 currRight++
                > 否则，currLeft = (currLeft + currRight + 1) / 2，并且设置 allSame = true
        5. 一旦 currRight >= s.length() 则程序结束
    时间复杂度：O(n)
    注意：
        1. 在判断由 allSame 转成 allSame = false 时，如：caaaaabde，还是会从 aaaaa 中间循环向后
        2. 代码较为复杂，条件语句较多
     */
    public String longestPalindromeME2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int currLeft = 0;
        int currRight = 1;
        int left = 0;
        int right = 0;
        boolean allSame = true;
        while (currRight < s.length()) {
            if (allSame) {
                if (s.charAt(currRight) == s.charAt(currRight - 1)) {
                    if (currRight - currLeft > right - left) {
                        left = currLeft;
                        right = currRight;
                    }
                    currRight++;
                } else {
                    if (currRight - currLeft + 1 <= 2) {
                        currRight++;
                        allSame = false;
                    } else {
                        if (currLeft != 0) {
                            currLeft--;
                            allSame = false;
                        } else {
                            currLeft = currRight++;
                        }
                    }
                }
            } else {
                if (s.charAt(currLeft) == s.charAt(currRight)) {
                    if (currRight - currLeft > right - left) {
                        left = currLeft;
                        right = currRight;
                    }
                    if (currLeft != 0) {
                        currLeft--;
                        currRight++;
                    } else {
                        currLeft = (currLeft + currRight) / 2 + 1;
                        currRight = currLeft + 1;
                        allSame = true;
                    }
                } else {
                    if (currRight - currLeft + 1 <= 2) {
                        currRight++;
                    } else {
                        currLeft = (currLeft + currRight) / 2 + 1;
                        currRight = currLeft + 1;
                        allSame = true;
                    }
                }
            }
        }
        return s.substring(left, right + 1);
    }
}
