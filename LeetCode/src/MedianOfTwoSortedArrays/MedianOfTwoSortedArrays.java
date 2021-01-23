package MedianOfTwoSortedArrays;

import org.junit.Test;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * Follow up: The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Example 3:
 * Input: nums1 = [0,0], nums2 = [0,0]
 * Output: 0.00000
 *
 * Example 4:
 * Input: nums1 = [], nums2 = [1]
 * Output: 1.00000
 *
 * Example 5:
 * Input: nums1 = [2], nums2 = []
 * Output: 2.00000
 *
 * Constraints:
 *      1. nums1.length == m
 *      2. nums2.length == n
 *      3. 0 <= m <= 1000
 *      4. 0 <= n <= 1000
 *      5. 1 <= m + n <= 2000
 *      6. -106 <= nums1[i], nums2[i] <= 106
 */
public class MedianOfTwoSortedArrays {

    /*
    Binary Search with Constraints
     */
    public double findMedianSortedArraysME(int[] nums1, int[] nums2) {
        //区分大小数组
        int m, n;
        int[] ms, ns;
        if (nums1.length > nums2.length) {
            ms = nums2;
            ns = nums1;
        } else {
            ms = nums1;
            ns = nums2;
        }
        m = ms.length;
        n = ns.length;

        if (m == 0 & n == 0) {
            return 0;
        } else if (m == 0) {
            if (n % 2 == 0) {
                return (double) (ns[n/2 - 1] + ns[n/2]) / 2;
            } else {
                return ns[n/2];
            }
        } else if (n == 0) {
            if (m % 2 == 0) {
                return (double) (ms[m/2 - 1] + ms[m/2]) / 2;
            } else {
                return ms[m/2];
            }
        }

        int imin = 0, imax = m;
        int[] split;
        split = binarySearch(ms, ns, imin, imax);
        if (split[0] == 0) {
            if ( (m + n) % 2 == 0) {
                return (double)(ns[split[1] - 1] + ((split[1] != n)? Math.min(ms[split[0]], ns[split[1]]) : ms[split[0]])) / 2;
            } else {
                return ns[split[1] - 1];
            }
        } else if (split[0] == m) {
            if ( (m + n) % 2 == 0) {
                return (double)(((split[1] != 0)? Math.max(ms[split[0] - 1], ns[split[1] - 1]) : ms[split[0] - 1]) + ns[split[1]]) / 2;
            } else {
                return Math.max(ms[split[0] - 1], ns[split[1] - 1]);
            }
        } else {
            if ( (m + n) % 2 == 0) {
                return (double)(Math.max(ms[split[0] - 1], ns[split[1] - 1]) + Math.min(ms[split[0]], ns[split[1]])) / 2;
            } else {
                return Math.max(ms[split[0] - 1], ns[split[1] - 1]);
            }
        }
    }
    //binarySearch 函数
    static int[] binarySearch(int[] ms, int[] ns, int imin, int imax) {
        int i = (imax + imin) / 2;
        //m + n 如果是偶数：正好满足；如果是奇数：左边正好多一位，符合要求
        int j = (ms.length + ns.length + 1) / 2 - i;
        if (i != 0 && i != ms.length) {
            if (ms[i - 1] <= ns[j] && ns[j - 1] <= ms[i]) {
                return new int[]{i, j};
            } else if (ms[i - 1] > ns[j]) {
                return binarySearch(ms, ns, imin, i - 1);
            } else {
                return binarySearch(ms, ns, i + 1, imax);
            }
        } else if (i == 0) {
            if (ns[j - 1] <= ms[i]) {
                return new int[]{i, j};
            } else {
                return binarySearch(ms, ns, i + 1, imax);
            }
        } else {
            if (ms[i - 1] <= ns[j]) {
                return new int[]{i, j};
            } else {
                return binarySearch(ms, ns, imin, i - 1);
            }
        }
    }

    @Test
    public void test1() {
        MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
        int[] a = new int[]{1, 4};
        int[] b = new int[]{2, 3, 5};
        System.out.println(m.findMedianSortedArraysME(a, b));
    }

    /*
    Solution：
        > 这里使用了 while 来代替 recursion
        > 代码比我的简洁多了
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
