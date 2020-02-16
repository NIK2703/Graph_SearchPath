package com.mo_171_ogurnoy_nikita.graph_search_path;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        //(new form()).init();

        double[][] data = {
                {Double.MAX_VALUE, 25, 40, 31, 27},
                {5, Double.MAX_VALUE, 17, 30, 25},
                {19, 15, Double.MAX_VALUE, 6, 1},
                {9, 50, 24, Double.MAX_VALUE, 6},
                {22, 8, 7, 10, Double.MAX_VALUE}
        };
        /*double[][] data = {
                {Double.MAX_VALUE, 90, 80, 40, 100},
                {60, Double.MAX_VALUE, 40, 50, 70},
                {50, 30, Double.MAX_VALUE, 60, 20},
                {10, 70, 20, Double.MAX_VALUE, 50},
                {20, 40, 50, 20, Double.MAX_VALUE}
        };*/

        Double[][] Data = new Double[data.length][];
        for (int i = 0; i < Data.length; i++) {
            Data[i] = new Double[data[0].length];
            for (int j = 0; j < Data[i].length; j++) {
                Data[i][j] = data[i][j];
            }
        }

        //Graph g = new Graph(TestTravelingSalesmanProblem.generateFullGraphEdgeWeight(5));
        Graph g = new Graph(Data);
        System.out.println(g.getSolutionTravelingSalesmanProblem());

    }
}