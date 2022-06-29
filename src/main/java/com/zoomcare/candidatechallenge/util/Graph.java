package com.zoomcare.candidatechallenge.util;

import com.zoomcare.candidatechallenge.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class Graph {

    @Autowired
    private EmployeeDAO empDao;

    private LinkedList<Integer>[] adj;                      //adjacency or neighbours
    private Queue<Integer> queue;

    public Graph() {
    }

    public Graph(int size) {
        adj = new LinkedList[size];
        for (int i = 0; i < adj.length; i++) adj[i] = new LinkedList<Integer>();
    }

    //adding an edge to the adjacency(proximity) list
    public void addEdge(int emp, int sup) {
        adj[emp].add(sup);
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Integer>[] adj) {
        this.adj = adj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Graph)) return false;
        Graph graph = (Graph) o;
        return Arrays.equals(adj, graph.adj);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(adj);
    }
}
