/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alpro;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

/**
 *
 * @author Wachid
 */
public class Graph {

    public static final int INFINITE = 999;
    private Map<Integer, Vertex> vertices = new TreeMap<>();
    private List<Edge> edges = new ArrayList<>();
    private int time;

    public Graph() {
        vertices.put(0, new Vertex(0));
    }
    
    public void BFS(Vertex s){
        System.out.println("Breadth-first search\n");
        for(Map.Entry<Integer, Vertex> u : vertices.entrySet()){
            if (u.getValue() != s){
                u.getValue().setDistance(INFINITE);
                System.out.println("Set distance of vertex "+u.getKey()+" to "+INFINITE);
                u.getValue().setPredecessor(null);
                System.out.println("Set predecessor of vertex "+u.getKey()+" to null");
            }
        }
        s.setColor(1);
        s.setDistance(0);
        s.setPredecessor(null);
        System.out.println("Set color of vertex "+s.getId()+" to gray");
        System.out.println("Set distance of vertex "+s.getId()+" to 0");
        System.out.println("Set predecessor of vertex "+s.getId()+" to null");
        Queue<Vertex> Q = new ArrayDeque<>();
        System.out.println("Creating a queue");
        Q.add(s);
        System.out.println("Enqueue vertex "+s.getId()+" to queue\n");
        System.out.print("Queue condition : ");
        for (Iterator<Vertex> it = Q.iterator(); it.hasNext();) { 
            Vertex i = it.next();
            System.out.print(i.getId()+" ");
        }
        System.out.println("");
        while (!Q.isEmpty()){
            Vertex u = Q.remove();
            System.out.println("Deque vertex "+u.getId()+" from queue");
            for(int v : u.getAdjacency()){
                if (vertices.get(v).getColor() == 0){
                    vertices.get(v).setColor(1); // set v's color to gray
                    vertices.get(v).setDistance(u.getDistance() + 1);
                    vertices.get(v).setPredecessor(u);
                    System.out.println("Set color of vertex "+v+" to gray");
                    System.out.println("Set distance of vertex "+v+" to "+(u.getDistance() + 1));
                    System.out.println("Set predecessor of vertex "+v+" to vertex "+u.getId());
                    Q.add(vertices.get(v));
                    System.out.println("Enqueue vertex "+v+" to queue\n");
                    System.out.print("Queue condition : ");
                    for (Iterator<Vertex> it = Q.iterator(); it.hasNext();) { 
                        Vertex i = it.next();
                        System.out.print(i.getId()+" ");
                    }
                    System.out.println("\n");
                }
            }
            u.setColor(2);
            System.out.println("Set color of vertex "+u.getId()+" to black");
        }
    }

    public void DFS(){
        System.out.println("\nDepth-First Search\n");
        for(Map.Entry<Integer, Vertex> u : vertices.entrySet()){
            u.getValue().setColor(0);
            u.getValue().setPredecessor(null);
            System.out.println("Vertex "+u.getValue().getId()+" : set color to white and set predecessor to null");
        }
        time = 0;
        System.out.println("Set time to 0\n");
        for(Map.Entry<Integer, Vertex> u : vertices.entrySet()){
           if (u.getValue().getColor() == 0){
               System.out.println("Call DFSVisit("+u.getValue().getId()+")\n");
               DFSVisit(u.getValue());
           }
        } 
    }
    
    private void DFSVisit(Vertex u){
        time++;
        System.out.println("Increment time to "+time);
        u.setDistance(time);
        u.setColor(1); // set color to gray
       System.out.println("Vertex "+u.getId()+" : set color to gray and set discovered time to "+time);
        for(int v : u.getAdjacency()){
            if (vertices.get(v).getColor() == 0){
                vertices.get(v).setPredecessor(u);
                System.out.println("Vertex "+v+" : set predecessor to "+u.getId());
                System.out.println("Call DFSVisit("+v+")\n");
                DFSVisit(vertices.get(v));
            }
        }
        u.setColor(2); // set color to black
        System.out.println("Vertex "+u.getId()+" : set color to black");
        time++;
        u.setFinished(time);
        System.out.println("Vertex "+u.getId()+" : set finished time to "+time);
    }
    
    public int pathDistance(Vertex a, Vertex b) {
        for (Edge e : edges) {
            if (e.getA() == a && e.getB() == b) {
                return e.getDistance();
            }
        }
        return INFINITE;
    }

    @Deprecated
    public void addNode(int predecessor_id, int distanceto) {
        Vertex newVertex = new Vertex(vertices.size());
        newVertex.setPredecessor(vertices.get(predecessor_id)); //not necessary
        vertices.put(vertices.size(), newVertex);
        edges.add(new Edge(vertices.get(predecessor_id), newVertex, distanceto));
    }

    public void addNode() {
        vertices.put(vertices.size(), new Vertex(vertices.size()));
    }

    public void addPath(int from, int to, int distance) {
        edges.add(new Edge(vertices.get(from), vertices.get(to), distance));
        vertices.get(from).getAdjacency().add(to);
        vertices.get(to).getAdjacency().add(from);
    }

    public void printPath(int startval, int vval) {
        Vertex start = vertices.get(startval);
        Vertex v = vertices.get(vval);
        printPath(start, v);
        System.out.println("");
    }

    public void printPath(Vertex start, Vertex v) {
        if (start == v) {
            System.out.print(" " + start.getId() + " ");
        } else if (v.getPredecessor() == null) {
            System.out.print("no path from " + start.getId() + " to " + v.getId() + " exist");
        } else {
            printPath(start, v.getPredecessor());
            System.out.print(" " + v.getId() + " ");
        }
    }

    /* Relaxation */
    public void initializeSingleSource(Vertex start) {
        for (Iterator<Entry<Integer, Vertex>> it = vertices.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Integer, Vertex> entry = it.next();
            entry.getValue().setDistance(INFINITE);
            entry.getValue().setPredecessor(null);
        }
        start.setDistance(0);
    }

    public void relax(Vertex u, Vertex v) {
        if (v.getDistance() > (u.getDistance() + pathDistance(u, v))) {
            v.setDistance(u.getDistance() + pathDistance(u, v));
            v.setPredecessor(u);
        }
    }

    /*---*/
    public boolean bellmanFord(int startval) {
        Vertex start = vertices.get(startval);
        
        initializeSingleSource(start);
        for (int i = 0; i < vertices.size()-1; i++) {
            for (Edge e : edges) {
                relax(e.getA(), e.getB());
            }
        }
        for (Edge e : edges) {
            if(e.getB().getDistance() > (e.getA().getDistance() + e.getDistance())){
                return false;
            }
        }
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph g = new Graph();
        Graph g1 = g;
        g.addNode();
        g.addNode();
        g.addNode();
        g.addNode();
        g.addNode();
        g.addNode();
        g.addNode();
        g.addNode();
        g.addPath(0, 1, 1);
        g.addPath(1, 0, 1);
        g.addPath(0, 4, 1);
        g.addPath(4, 0, 1);
        g.addPath(1, 5, 1);
        g.addPath(5, 1, 1);
        g.addPath(5, 6, 1);
        g.addPath(6, 5, 1);
        g.addPath(2, 5, 1);
        g.addPath(5, 2, 1);
        g.addPath(2, 6, 1);
        g.addPath(6, 2, 1);
        g.addPath(5, 2, 1);
        g.addPath(3, 2, 1);
        g.addPath(2, 3, 1);
        g.addPath(6, 7, 1);
        g.addPath(7, 6, 1);
        g.addPath(3, 6, 1);
        g.addPath(6, 3, 1);
        g.addPath(3, 7, 1);
        g.addPath(7, 3, 1);
        g.BFS(g.vertices.get(1));
        g1.DFS();
        /*g.addNode();
        g.addNode();
        g.addNode();
        g.addNode();
        g.addPath(0, 1, 6);
        g.addPath(0, 3, 7);
        g.addPath(1, 2, 5);
        g.addPath(1, 3, 8);
        g.addPath(1, 4, -4);
        g.addPath(2, 1, -2);
        g.addPath(3, 2, -3);
        g.addPath(3, 4, 9);
        g.addPath(4, 0, 2);
        g.addPath(4, 2, 7);
        g.bellmanFord(0);
        g.printPath(0, 4);*/
    }
}




class Vertex {

    private int distance;
    private Vertex predecessor;
    private int id;
    private int color; // 0 = white; 1 = gray; 2 = black
    private List<Integer> adjacency = new ArrayList<>();
    private int finished;

    public Vertex(int id) {
        distance = 0;
        predecessor = null;
        this.id = id;
        color = 0;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return the predecessor
     */
    public Vertex getPredecessor() {
        return predecessor;
    }

    /**
     * @param predecessor the predecessor to set
     */
    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @return the adjacency
     */
    public List<Integer> getAdjacency() {
        return adjacency;
    }

    /**
     * @param adjacency the adjacency to set
     */
    public void setAdjacency(List<Integer> adjacency) {
        this.adjacency = adjacency;
    }

    /**
     * @return the finished
     */
    public int getFinished() {
        return finished;
    }

    /**
     * @param finished the finished to set
     */
    public void setFinished(int finished) {
        this.finished = finished;
    }
}

class Edge {

    private Vertex a;
    private Vertex b;
    private int distance;

    public Edge(Vertex a, Vertex b, int distance) {
        this.a = a;
        this.b = b;
        this.distance = distance;
    }

    /**
     * @return the a
     */
    public Vertex getA() {
        return a;
    }

    /**
     * @return the b
     */
    public Vertex getB() {
        return b;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }
}
