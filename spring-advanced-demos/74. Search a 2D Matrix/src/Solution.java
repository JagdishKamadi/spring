class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = -1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == target || target == matrix[i][matrix[i].length - 1]) {
                return true;
            } else if (matrix[i][0] < target && target < matrix[i][matrix[i].length - 1]) {
                rowIndex = i;
                break;
            }
        }
        if (rowIndex == -1) {
            return false;
        }
        int left = 0;
        int right = matrix[rowIndex].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[rowIndex][mid] == target) {
                return true;
            } else if (matrix[rowIndex][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 13;
        System.out.println(new Solution().searchMatrix(matrix, target));
    }
}