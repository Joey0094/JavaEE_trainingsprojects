package AddTwoNumbers;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 * Constraints:
 *      1. The number of nodes in each linked list is in the range [1, 100].
 *      2. 0 <= Node.val <= 9
 *      3. It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {

    /*
    我的初始版本：
        > 代码复杂，但是使用内存比下方版本少
     */
    public ListNode addTwoNumbersME(ListNode l1, ListNode l2) {
        ListNode ln1 = l1;
        int len1 = 0;
        while (ln1 != null) {
            len1++;
            ln1 = ln1.next;
        }
        ListNode ln2 = l2;
        int len2 = 0;
        while (ln2 != null) {
            len2++;
            ln2 = ln2.next;
        }
        int min, max;
        min = (len1 < len2)? len1 : len2;
        max = (len1 > len2)? len1 : len2;

        ln1 = l1;
        ln2 = l2;
        int sum;
        int res;
        ListNode sumList = new ListNode(0);
        //用于存储 sumList 的位置
        ListNode sln = sumList;
        //用于存储上一个 sln 的位置
        ListNode slnb = sln;
        //重合的部分 Sum 运算
        for (int i = 0; i < min; i++) {
            sum = ln1.val + ln2.val + sln.val;
            if (sum > 9) {
                res = sum - 10;
                sln.val = res;
                sln.next = new ListNode(1);
            } else {
                sln.val = sum;
                sln.next = new ListNode(0);
            }
            slnb = sln;
            sln = sln.next;
            ln1 = ln1.next;
            ln2 = ln2.next;
        }
        //处理多出来的部分
        if (max > min) {
            for (int i = 0; i < max - min; i++) {
                if (max == len1) {
                    sum = ln1.val + sln.val;
                    if (sum > 9) {
                        res = sum - 10;
                        sln.val = res;
                        sln.next = new ListNode(1);
                    } else {
                        sln.val = sum;
                        sln.next = new ListNode(0);
                    }
                    slnb = sln;
                    sln = sln.next;
                    ln1 = ln1.next;
                } else {
                    sum = ln2.val + sln.val;
                    if (sum > 9) {
                        res = sum - 10;
                        sln.val = res;
                        sln.next = new ListNode(1);
                    } else {
                        sln.val = sum;
                        sln.next = new ListNode(0);
                    }
                    slnb = sln;
                    sln = sln.next;
                    ln2 = ln2.next;
                }
            }
        }

        //不可如此，sln 是 reference，将它设置为 null 对于原来指向的对象没有影响
//        sln = null;
        //该方法需要多一个存储位置
        if (slnb.next.val == 0) {
            slnb.next = null;
        }

        return  sumList;
    }

    /*
    优化版本：
        > 减少了代码复杂度
        > 但是内存占用增加了？？？
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int x, y, carry = 0, sum;
        while (p != null || q != null) {
            x = (p != null)? p.val : 0;
            y = (q != null)? q.val : 0;
            sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) { p = p.next; }
            if (q != null) { q = q.next; }
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
