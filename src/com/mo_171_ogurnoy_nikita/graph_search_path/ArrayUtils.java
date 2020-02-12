package com.mo_171_ogurnoy_nikita.graph_search_path;

public class ArrayUtils {
    public static Double adductMatrix (Double[][] matrix) {
        Double h = 0.0;
        for(int i = 0; i < matrix.length; i++) {
            Double minVal = Double.MAX_VALUE;
            int minInd = 0;
            for (int j = 0 ; j < matrix[i].length; j++) {
                if (matrix[i][j] < minVal) {
                    minVal = matrix[i][j];
                    minInd = j;
                }
            }
            if (minVal == Double.MAX_VALUE)
            {
                continue;
            }
            for (int j = 0 ; j < matrix[i].length; j++) {
                if(matrix[i][j] != Double.MAX_VALUE) {
                    matrix[i][j] -= minVal;
                }
            }
            h += minVal;
        }
        for(int i = 0; i < matrix[0].length; i++) {
            Double minVal = Double.MAX_VALUE;
            int minInd = 0;
            for (int j = 0 ; j < matrix.length; j++) {
                if (matrix[j][i] < minVal) {
                    minVal = matrix[j][i];
                    minInd = i;
                }
            }
            if (minVal == Double.MAX_VALUE)
            {
                continue;
            }
            for (int j = 0 ; j < matrix.length; j++) {
                if(matrix[j][i] != Double.MAX_VALUE) {
                    matrix[j][i] -= minVal;
                }
            }
            h += minVal;
        }
        return h;
    }

    public static Double[][] deleteRow(Double[][] matrix, int row) {
        Double[][] newMatrix = new Double[matrix.length - 1][];
        for (int i = 0; i < row; i++) {
            newMatrix[i] = matrix[i];
        }
        for(int i = row + 1; i < matrix.length; i++) {
            newMatrix[i-1] = matrix[i];
        }
        return newMatrix;
    }

    public static Double[][] deleteColumn(Double[][] matrix, int column) {
        Double[][] newMatrix = new Double[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            newMatrix[i] = new Double[matrix[i].length - 1];
            for (int j = 0; j < column; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
            for (int j = column + 1; j < matrix[i].length; j++) {
                newMatrix[i][j - 1] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static Double[][] copy(Double[][] matrix) {
        Double [][] newMatrix = new Double[matrix.length][];
        for(int i = 0; i < matrix.length; i++)
            newMatrix[i] = matrix[i].clone();
        return newMatrix;
    }

    public static Double getMinInRow(Double[][] matrix, int row) {
        Double min = Double.MAX_VALUE;
        for(int i = 0; i < matrix[row].length; i++) {
            min = Math.min(min, matrix[row][i]);
        }
        return min;
    }

    public static Double getMinInColumn(Double[][] matrix, int column) {
        Double min = Double.MAX_VALUE;
        for(int i = 0; i < matrix.length; i++) {
            min = Math.min(min, matrix[i][column]);
        }
        return min;
    }

    public static int[] getMinElementIndex(Double[][] matrix) {
        int indRow = 0;
        int indColumn = 0;
        Double minVal = Double.MAX_VALUE;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < minVal) {
                    minVal = matrix[i][j];
                    indRow = i;
                    indColumn = j;
                }
            }
        }
        int[] index = {indRow, indColumn};
        return index;
    }
}
