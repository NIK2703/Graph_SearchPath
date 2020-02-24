package com.mo_171_ogurnoy_nikita.graph_search_path;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        //(new form()).init();

        /*double[][] data = {
                {Double.MAX_VALUE, 25, 40, 31, 27},
                {5, Double.MAX_VALUE, 17, 30, 25},
                {19, 15, Double.MAX_VALUE, 6, 1},
                {9, 50, 24, Double.MAX_VALUE, 6},
                {22, 8, 7, 10, Double.MAX_VALUE}
        };*/
        /*double[][] data = {
                {Double.MAX_VALUE, 90, 80, 40, 100},
                {60, Double.MAX_VALUE, 40, 50, 70},
                {50, 30, Double.MAX_VALUE, 60, 20},
                {10, 70, 20, Double.MAX_VALUE, 50},
                {20, 40, 50, 20, Double.MAX_VALUE}
        };*/
        double[][] data = {
                {Double.MAX_VALUE, 10, 15, 11, 2, 55},
                {17, Double.MAX_VALUE, 16, 18, 21, 13},
                {10, 50, Double.MAX_VALUE, 39, 22, 3},
                {28, 29, 24, Double.MAX_VALUE, 28, 25},
                {27, 9, 32, 9, Double.MAX_VALUE, 2},
                {43, 48, 40, 43, 21, Double.MAX_VALUE}
        };
        /*double[][] data = {
                {Double.MAX_VALUE, 4, 10, 13},
                {2, Double.MAX_VALUE, 9, 7},
                {8, 5, Double.MAX_VALUE, 5},
                {5, 8, 5, Double.MAX_VALUE}
        };*/
        /*double[][] data = {
                {Double.MAX_VALUE, 0.0868764474320748, 0.3567329304264734, 0.40123054166937433, 0.39337324077699304, 0.5305150704006775},
                {0.0868764474320748, Double.MAX_VALUE, 0.4106541290440209, 0.4500780598618982, 0.44893465128694005, 0.5921868492719833},
                {0.3567329304264734, 0.4106541290440209, Double.MAX_VALUE, 0.05126346457225154, 0.03883773886179244, 0.18651671893504654},
                {0.40123054166937433, 0.4500780598618982, 0.05126346457225154, Double.MAX_VALUE, 0.04113317688553608, 0.1651382239307932},
                {0.39337324077699304, 0.44893465128694005, 0.03883773886179244, 0.04113317688553608, Double.MAX_VALUE, 0.14793219971824598},
                {0.5305150704006775, 0.5921868492719833, 0.18651671893504654, 0.1651382239307932, 0.14793219971824598, Double.MAX_VALUE}
        };*/


        Double[][] Data = new Double[data.length][];
        for (int i = 0; i < Data.length; i++) {
            Data[i] = new Double[data[0].length];
            for (int j = 0; j < Data[i].length; j++) {
                Data[i][j] = data[i][j];
            }
        }

        Graph g = new Graph(TestTravelingSalesmanProblem.generateFullGraphEdgeWeight(10));
        //Graph g = new Graph(Data);
        System.out.println(g.getSolutionTravelingSalesmanProblem());

    }
}