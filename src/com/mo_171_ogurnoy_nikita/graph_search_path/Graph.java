package com.mo_171_ogurnoy_nikita.graph_search_path;

import java.util.*;

public class Graph {
    Double weightEdge[][];

    Graph(Double weightEdge[][]){
        int vnum = weightEdge.length;
        this.weightEdge = new Double[vnum][vnum];
        System.arraycopy(weightEdge, 0, this.weightEdge, 0, vnum);
    }

    //getShortestPath
    ArrayList<Path> getShortestPath(Integer start, Integer target){
        return getShortestPath(start, target, weightEdge);
    }

    ArrayList<Path> getShortestPath(Integer start, Integer target, Double[][] weightEdge){
        int vnum = weightEdge.length;

        Double[] valueMark = new Double[vnum];
        java.util.Arrays.fill(valueMark,Double.MAX_VALUE);
        valueMark[start] = 0.0;

        Boolean[] constMark = new Boolean[vnum];
        java.util.Arrays.fill(constMark,false);
        constMark[start] = true;

        Integer vcurr = start; //текущая вершиная по алгориитму Дейкстры
        double minLength = Double.MAX_VALUE;

        for(int i = 0; i < vnum; i++){
            int minMark = -1;
            Double minValMark = Double.MAX_VALUE;

            for (int j = 0; j < vnum; j++) {
                if(constMark[j]) continue;

                valueMark[j] = Math.min(valueMark[j], valueMark[vcurr] + weightEdge[vcurr][j]);
                if (valueMark[j]<minValMark){
                    minMark = j;
                    minValMark = valueMark[j];
                }
            }

            if(minMark<0) return new ArrayList<Path>();

            constMark[minMark] = true;
            vcurr = minMark;

            if (minMark == target) {
                minLength = minValMark;
                break;
            }
        }

        ArrayList<Path> spaths = new ArrayList<Path>();
        spaths.add(getPrevVertex(vcurr,new Path(minLength, new ArrayList<Integer>()), spaths,valueMark,vnum,weightEdge));

        return spaths;
    }

    Path getPrevVertex(int v, Path prevPath, ArrayList<Path> pathList, Double[] valueMark, int vnum, Double[][] weightEdge) {
        ArrayList<Integer> prevVertex = new ArrayList<Integer>();
        Path path = new Path(prevPath);

        if (valueMark[v] == 0) {
            path.vseq.add(0, v);
            return path;
        }

        for (int i = 0; i < vnum; i++) {
            if ((v != i) && (valueMark[v] - weightEdge[i][v] == valueMark[i]))
                prevVertex.add(0, i);
        }
        int len = prevVertex.size();

        path.vseq.add(0, v);
        for (int i = 0; i < len; i++) {
            if (i != len - 1) pathList.add(getPrevVertex(prevVertex.get(i), path, pathList, valueMark,vnum,weightEdge));
            return getPrevVertex(prevVertex.get(len - 1), path, pathList, valueMark, vnum,weightEdge);
        }
        return null;
    }

    ArrayList<Path> getShortestPath(Integer start, Integer target, Integer number){
        ArrayList<Path> L0, L, P;
        Set<Path> L1;
        L = new ArrayList<Path>(getShortestPath(start,target));
        if(L.size() >= number)
            return new ArrayList<Path>(L.subList(0,number));

        L0 = new ArrayList<Path>(L.subList(0,1));
        L1 = new HashSet<Path>(L.subList(1,L.size()));
        P = new ArrayList<Path>(L0);

        for(int k=1; k < number; k++){
            L1.addAll(getPathDeviations(P.get(k-1),P));
            double minLen = Double.MAX_VALUE;
            Path shortestL1 = null;
            for(Path p:L1)
                if (p.val < minLen) {
                        minLen = p.val;
                        shortestL1 = p;
                }

            if(shortestL1 == null) break;
            L0.add(shortestL1);
            P.add(shortestL1);
            L1.remove(shortestL1);
        }

        return L0;
    }

    ArrayList<Path> getPathDeviations(Path direct, ArrayList<Path> P){
        int vnum = weightEdge.length;
        ArrayList<Path> devpath = new ArrayList<Path>();
        Integer pathVnum = direct.vseq.size();
        for(int i=1; i<=pathVnum-1; i++){
            Double[][] restrictWeightEdge = new Double[vnum][vnum];
            for(int q=0; q<vnum; q++)
                for(int w=0; w<vnum; w++)
                    restrictWeightEdge[q][w] = weightEdge[q][w];
            direct.blockRoot(restrictWeightEdge, i);
            for(Path p: P){
                if(p.vseq.size()>=i && direct.vseq.subList(0,i).equals(p.vseq.subList(0,i)))
                    p.blockEdge(restrictWeightEdge,p.vseq.get(i-1),p.vseq.get(i));
            }

            ArrayList<Path> newDevpath = new ArrayList<Path>(getShortestPath(direct.vseq.get(i-1),
                    direct.vseq.get(direct.vseq.size()-1),restrictWeightEdge));
            Double sublen = direct.subLen(i,weightEdge);
            for(Path ndp: newDevpath) {
                ndp.vseq.addAll(0, direct.vseq.subList(0, i - 1));
                ndp.val+=sublen;
            }

            devpath.addAll(newDevpath);

        }

        return devpath;
    }

    //getSolutionTravelingSalesmanProblem
    public Path getSolutionTravelingSalesmanProblem() {
//        if(!isFull()) {
//            return null;
//        }

        ArrayList<Integer> vertexPath = new ArrayList<>();
        Double[][] matrix = ArrayUtils.copy(weightEdge);
        int startVertex = 0;

        Double h = ArrayUtils.adductMatrix(matrix);
        HashMap<Integer, Integer> includedEdges = new HashMap<>();
        HashMap<Integer, Integer> excludedEdges = new HashMap<>();
        while (includedEdges.size() < weightEdge.length) {
            //ArrayUtils.print(matrix);
            if(ArrayUtils.isNull(matrix)) {
                ArrayList<Integer> connectEdges = ArrayUtils.getNullIndexes(matrix);
                for (int i = 0; i < connectEdges.size(); i += 2) {
                    if(!includedEdges.values().contains(connectEdges.get(i+1))) {
                        includedEdges.put(connectEdges.get(i), connectEdges.get(i + 1));
                    }
                }
                vertexPath.add(startVertex);
                while (vertexPath.size() < includedEdges.size() + 1) {
                    vertexPath.add(includedEdges.get(vertexPath.get(vertexPath.size() - 1)));
                }
                break;
            }
            int[] edge = ArrayUtils.getMinElementIndexMaxW(matrix);
            Double excludeWDelta = matrix[edge[2]][edge[3]] + matrix[edge[4]][edge[5]];

            Path.blockOut(matrix, edge[0]);
            Path.blockIn(matrix, edge[1]);
            Path.blockEdge(matrix, edge[1], edge[0]);

            Double excludeEdgeH = h + excludeWDelta;
            h += ArrayUtils.adductMatrix(matrix);

            if (h < excludeEdgeH || h.equals(excludeEdgeH)) {
                if (includedEdges.size() == 0) {
                    startVertex = edge[0];
                }
                includedEdges.put(edge[0], edge[1]);
                /*for(Integer visitedVertex : includedEdges.keySet()) {
                    if(matrix[edge[1]][visitedVertex] == 0 && visitedVertex != startVertex) {
                        Path.blockEdge(matrix, edge[1], visitedVertex);
                    }
                }*/
            }
            else {
                excludedEdges.put(edge[0], edge[1]);
            }
            Path.blockEdge(matrix, edge[0], edge[1]);
        }

        return new Path(h, vertexPath);
    }

    public boolean isFull () {
        for (int i = 0; i < weightEdge.length; i++) {
            for (int j = 0; j < weightEdge.length; j++) {
                if ((i != j) && (weightEdge.equals(Double.MAX_VALUE))) {
                    return false;
                }
            }
        }
        return true;
    }
}
