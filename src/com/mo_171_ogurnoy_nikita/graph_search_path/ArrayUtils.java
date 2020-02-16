package com.mo_171_ogurnoy_nikita.graph_search_path;

public class ArrayUtils {
    public static Double adductMatrix (Double[][] matrix) {
        Double h = 0.0;
        for(int i = 0; i < matrix.length; i++) {
            Double minVal = Double.MAX_VALUE;
            for (int j = 0 ; j < matrix[i].length; j++) {
                minVal = Math.min(minVal, matrix[i][j]);
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
            for (int j = 0 ; j < matrix.length; j++) {
                minVal = Math.min(minVal, matrix[j][i]);
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

    public static int[] getMinElementIndexMaxW(Double[][] matrix) {
        int[] out = new int[6];
        double maxW = Double.MIN_VALUE;
        Double minVal = Double.MAX_VALUE;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] <= minVal) {
                    //if(matrix[i][j].equals(minVal)) {
                        double minWRow = Double.MAX_VALUE;
                        double minWColumn = Double.MAX_VALUE;
                        int indWMinRowX = 0;
                        int indWMinRowY = 0;
                        int indWMinColumnX = 0;
                        int indWMinColumnY = 0;
                        for(int k = 0; k < matrix[i].length; k++) {
                            if(matrix[i][k] != Double.MAX_VALUE && k != j) {
                                if(matrix[i][k] < minWRow) {
                                    minWRow = matrix[i][k];
                                    indWMinRowX = i;
                                    indWMinRowY = k;
                                }
                            }
                        }
                        for(int k = 0; k < matrix.length; k++) {
                            if(matrix[k][j] != Double.MAX_VALUE && k != i) {
                                if(matrix[k][j] < minWColumn) {
                                    minWColumn = matrix[k][j];
                                    indWMinColumnX = k;
                                    indWMinColumnY = j;
                                }
                            }
                        }
                        if (minWRow == Double.MAX_VALUE || minWColumn == Double.MAX_VALUE) {
                            continue;
                        }
                        double w = minWRow + minWColumn;
                        if(w > maxW) {
                            maxW = w;
                            out[2] = indWMinRowX;
                            out[3] = indWMinRowY;
                            out[4] = indWMinColumnX;
                            out[5] = indWMinColumnY;
                            minVal = matrix[i][j];
                            out[0] = i;
                            out[1] = j;
                        }
                        //else continue;
                    //}
                }
            }
        }
        return out;
    }

    public static void print(Double[] arr) {
        System.out.print("[");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if(i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public static void print(Double[][] matrix) {
        System.out.println("[");
        for(int i = 0; i < matrix.length; i++) {
            System.out.print("  ");
            print(matrix[i]);
            if(i != matrix.length - 1) {
                System.out.print(",");
            }
            System.out.println();
        }
        System.out.println("]");
    }
}
