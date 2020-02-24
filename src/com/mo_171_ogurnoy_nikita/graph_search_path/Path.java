package com.mo_171_ogurnoy_nikita.graph_search_path;

import java.util.*;

public class Path{
    public Double val;
    public ArrayList<Integer> vseq;

    Path(Double len, ArrayList<Integer> vseq){
        this.val = len;
        this.vseq = (ArrayList<Integer>) vseq.clone();
    }

    Path(Path p){
        this.val = p.val;
        this.vseq = (ArrayList<Integer>) p.vseq.clone();
    }



    /*Path(HashMap<Integer, Integer> includedEdges, int start, Double val) {
        vseq = new ArrayList<>();
        vseq.add(start);
        while (vseq.size() < includedEdges.size() + 1) {
            vseq.add(includedEdges.get(vseq.get(vseq.size() - 1)));
        }
        this.val = val;
    }*/

    public static Path getLoop(HashMap<Integer, Integer> includedEdges, int startVertex, Double val) {
        ArrayList<Integer> vertexPath = new ArrayList<>();
        vertexPath.add(startVertex);
        HashSet<Integer> visitedVertex = new HashSet<>();
        visitedVertex.add(startVertex);
        while (vertexPath.size() < includedEdges.size() + 1) {
            vertexPath.add(includedEdges.get(vertexPath.get(vertexPath.size() - 1)));
            if (visitedVertex.contains(includedEdges.get(vertexPath.get(vertexPath.size() - 2)))) {
                break;
            }
            visitedVertex.add(includedEdges.get(vertexPath.get(vertexPath.size() - 2)));
        }
        return (vertexPath.get(0) == vertexPath.get(vertexPath.size() - 1) ? new Path(val, vertexPath) : null);
    }

    Double subLen(int len, Double[][] weightEdge){
        double sublen = 0;
        for(int i=0; i<len-1; i++)
            sublen += weightEdge[vseq.get(i)][vseq.get(i+1)];
        return sublen;
    }


    static void blockVertex(Double[][] weightEdge, Integer index) {
        blockOut(weightEdge, index);
        blockIn(weightEdge,index);
    }

    static void blockOut(Double[][] weightEdge, Integer index) {
        Integer vnum = weightEdge.length;
        for(int i=0; i<vnum; i++)
            weightEdge[index][i] = Double.MAX_VALUE;
    }

    static void blockIn(Double[][] weightEdge, Integer index) {
        Integer vnum = weightEdge.length;
        for(int i=0; i<vnum; i++)
            weightEdge[i][index] = Double.MAX_VALUE;
    }

    static void blockEdge(Double[][] weightEdge, Integer vout, Integer vin){
        weightEdge[vout][vin] = Double.MAX_VALUE;
    }

    void blockRoot(Double[][] weightEdge, Integer rootLen){
        for(int i = 0; i<rootLen-1; i++)
            blockVertex(weightEdge,vseq.get(i));
    }

    public String toString() {
        return val + " - " + vseq.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Objects.equals(val, path.val) &&
                Objects.equals(vseq, path.vseq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, vseq);
    }
}
