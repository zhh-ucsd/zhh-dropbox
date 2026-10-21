/*
 * Name: Zhiyuan Huang
 * Email: zhh087@ucsd.edu
 * PID: 200336800
 * Sources used: pa2-starter
 *
 * My implementation of ArrayList.
 */

/**
 * A concrete class that implements a static method utilizing MyArrayList
 */
public class MyAlgorithm {

    /**
     * Moves all occurrences of the target integer to the end of the list 
     * in-place. O(n)
     * 
     * @param nums   The MyArrayList of integers.
     * @param target The integer value to move to the end.
     * @return The modified MyArrayList with all occurrences of the target 
     *         integer moved to the end.
     */
    public static MyArrayList<Integer> moveTargetToEnd(
        MyArrayList<Integer> nums, int target) {
        Integer t = target;
        int i = 0, j = 0; // the gap between i and j is target
        while(i<nums.size()){
            if(!t.equals(nums.get(i))){ // if saw a non-target, move before the targets
                nums.set(j,nums.get(i));
                j++; // advance when non-target is added
            }
            i++;
        }

        while(j<nums.size()) nums.set(j++,t);
        return nums;
    }

    /**
     * Moves all occurrences of the target integer to the end of the list 
     * in-place but slower. O(n^2)
     * 
     * @param nums   The MyArrayList of integers.
     * @param target The integer value to move to the end.
     * @return The modified MyArrayList with all occurrences of the target 
     *         integer moved to the end.
     */
    public static MyArrayList<Integer> moveTargetToEndSlow(
            MyArrayList<Integer> nums, int target) {
        if (nums == null) {
            throw new NullPointerException("nums is null");
        }
        Integer tgt = target, j = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (tgt.equals(nums.get(i))) { // null wont give error, otherwise it's Integer vs int
                j = i + 1;
                while (j < nums.size() && tgt.equals(nums.get(j)))
                    j++;
            }
            if (j == nums.size())
                return nums;
            Integer tmp = nums.get(j);
            nums.set(j, nums.get(i));
            nums.set(i, tmp);
        }
        return nums;
    }
}
