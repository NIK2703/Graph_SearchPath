package com.mo_171_ogurnoy_nikita.graph_search_path;

import java.util.Random;

public class TestTravelingSalesmanProblem {
    public static Double[][] generateFullGraphEdgeWeight (int vertexNum) {
        float vertexX[] = new float[vertexNum];
        float vertexY[] = new float[vertexNum];

        Random rand = new Random();
        for (int i = 0; i < vertexNum; i++) {
            vertexX[i] = rand.nextFloat();
            vertexY[i] = rand.nextFloat();
        }

        Double[][] weightEdge = new Double[vertexNum][];
        for (int i = 0; i < vertexNum; i++) {
            weightEdge[i] = new Double[vertexNum];
        }
        for (int i = 0; i < weightEdge.length; i++) {
            for (int j = 0; j < weightEdge[0].length; j++) {
                if(i == j) {
                    weightEdge[i][j] = Double.MAX_VALUE;
                    continue;
                }
                weightEdge[i][j] = weightEdge[j][i] = Math.sqrt(Math.pow(vertexX[j] - vertexX[i], 2)
                        + Math.pow(vertexY[j] - vertexY[i], 2));
            }
        }

        return weightEdge;
    }
}
