class Solution {
    public void sortColors(int[] nums) {
        int countZeros = 0;
        int countOnes = 0;
        int countTwos = 0;
        for (int i : nums) {
            if (i == 0) {
                countZeros++;
            } else if (i == 1) {
                countOnes++;
            } else {
                countTwos++;
            }
        }
        int counter = 0;
        for (int i = 0; i < countZeros; i++) {
            nums[counter++] = 0;
        }
        for (int i = 0; i < countOnes; i++) {
            nums[counter++] = 1;
        }
        for (int i = 0; i < countTwos; i++) {
            nums[counter++] = 2;
        }
    }
}