package maximumFlow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class MaximumFlow {

    public static void main(String[] args) {
        boolean finish = false;
        Scanner sc = new Scanner(System.in);
        int source;
        int sink;
        DirectedGraph g;
        HashMap<Edge, Integer> flow;
        do {
            System.out.println("1. Graf baru");
            System.out.println("2. Contoh graf dari buku");
            System.out.println("0. Selesai");
            int pilihan = sc.nextInt();
            if (pilihan == 1) {
                g = new DirectedGraph();
                System.out.println("Input graf");
                System.out.print("Node source: ");
                source = sc.nextInt();
                System.out.print("Node sink: ");
                sink = sc.nextInt();
                while (true) {
                    System.out.print("Start: ");
                    int start = sc.nextInt();
                    System.out.print("Target: ");
                    int target = sc.nextInt();
                    System.out.print("Kapasitas: ");
                    int capacity = sc.nextInt();
                    g.addEdge(start, target, capacity);
                    System.out.println("Tambah data lagi? (1 / 0)");
                    if (sc.nextInt() == 0) {
                        break;
                    }
                }
                flow = getMaxFlow(g, source, sink);
                System.out.println("Maximum flow: " + getFlowSize(flow, g, source));
            } else if (pilihan == 2) {
                // Graph 0, maximum flow is 23
                source = 0;
                sink = 5;
                g = new DirectedGraph();
                g.addEdge(0, 1, 16);
                g.addEdge(0, 2, 13);
                g.addEdge(1, 3, 12);
                g.addEdge(2, 1, 4);
                g.addEdge(2, 4, 14);
                g.addEdge(3, 2, 9);
                g.addEdge(3, 5, 20);
                g.addEdge(4, 3, 7);
                g.addEdge(4, 5, 4);

                flow = getMaxFlow(g, source, sink);
                System.out.println("Maximum flow: " + getFlowSize(flow, g, source));
            } else if (pilihan == 0) {
                finish = true;
            }
        } while (!finish);


//        // Graph 1, maximum flow is 6
//        source = 1;
//        sink = 4;
//        g = new DirectedGraph();
//        g.addEdge(1, 2, 4);
//        g.addEdge(1, 3, 2);
//        g.addEdge(2, 4, 1);
//        g.addEdge(2, 3, 3);
//        g.addEdge(3, 4, 6);
//        flow = getMaxFlow(g, source, sink);
//        System.out.println(getFlowSize(flow, g, source));
//
//        // Graph 2, maximum flow is 31
//        g = new DirectedGraph();
//        source = 0;
//        sink = 7;
//        g.addEdge(0, 1, 38);
//        g.addEdge(0, 2, 1);
//        g.addEdge(0, 6, 2);
//        g.addEdge(1, 2, 8);
//        g.addEdge(1, 4, 13);
//        g.addEdge(1, 3, 10);
//        g.addEdge(2, 3, 26);
//        g.addEdge(3, 6, 24);
//        g.addEdge(3, 5, 8);
//        g.addEdge(3, 7, 1);
//        g.addEdge(4, 2, 2);
//        g.addEdge(4, 7, 7);
//        g.addEdge(4, 5, 1);
//        g.addEdge(5, 7, 7);
//        g.addEdge(6, 7, 27);
//        flow = getMaxFlow(g, source, sink);
//        System.out.println(getFlowSize(flow, g, source));
//
//        // Graph 3, maximum flow is 16
//        g = new DirectedGraph();
//        source = 1;
//        sink = 6;
//        g.addEdge(1, 3, 0);
//        g.addEdge(1, 2, 6);
//        g.addEdge(1, 4, 5);
//        g.addEdge(1, 5, 5);
//        g.addEdge(2, 4, 3);
//        g.addEdge(2, 5, 1);
//        g.addEdge(2, 6, 3);
//        g.addEdge(3, 2, 3);
//        g.addEdge(3, 4, Integer.MAX_VALUE);
//        g.addEdge(3, 5, 2);
//        g.addEdge(3, 6, 9);
//        g.addEdge(4, 3, Integer.MAX_VALUE);
//        g.addEdge(4, 6, 0);
//        g.addEdge(5, 4, 3);
//        g.addEdge(5, 6, 4);
//        flow = getMaxFlow(g, source, sink);
//        System.out.println(getFlowSize(flow, g, source));
//
//        // Graph 4, some random shit
//        g = new DirectedGraph();
//        source = 1;
//        sink = 100;
//        for (int i = 0; i < 100000; i++) {
//            g.addEdge((int) (Math.random() * sink + 1),
//                    (int) (Math.random() * sink + 1),
//                    (int) (Math.random() * 500));
//        }
//        flow = getMaxFlow(g, source, sink);
//        System.out.println(getFlowSize(flow, g, source));
    }

    static HashMap<Edge, Integer> getMaxFlow(DirectedGraph g, Object source,
            Object sink) {
        // The path from source to sink that is found in each iteration
        LinkedList<Edge> path;
        // The flow, i.e. the capacity of each edge that is actually used
        HashMap<Edge, Integer> flow = new HashMap<>();
        // Create initial empty flow.
        for (Edge e : g.getEdges()) {
            flow.put(e, 0);
        }

        // The Algorithm itself
        while ((path = bfs(g, source, sink, flow)) != null) {
            // Activating this output will illustrate how the algorithm works
            // System.out.println(path);
            // Find out the flow that can be sent on the found path.
            int minCapacity = Integer.MAX_VALUE;
            Object lastNode = source;
            //System.out.println("Path: "+path.toString());
            for (Edge edge : path) {
                int c;
                // Although the edges are directed they can be used in both
                // directions if the capacity is partially used, so this if
                // statement is necessary to find out the edge's actual
                // direction.
                if (edge.getStart().equals(lastNode)) {
                    c = edge.getCapacity() - flow.get(edge);
                    lastNode = edge.getTarget();
                } else {
                    c = flow.get(edge);
                    lastNode = edge.getStart();
                }
                if (c < minCapacity) {
                    minCapacity = c;
                }
            }

            // Change flow of all edges of the path by the value calculated
            // above.
            lastNode = source;
            for (Edge edge : path) {
                // If statement like above
                if (edge.getStart().equals(lastNode)) {
                    flow.put(edge, flow.get(edge) + minCapacity);
                    lastNode = edge.getTarget();
                } else {
                    flow.put(edge, flow.get(edge) - minCapacity);
                    lastNode = edge.getStart();
                }
            }
        }

        return flow;
    }

    static int getFlowSize(HashMap<Edge, Integer> flow, DirectedGraph g,
            Object source) {
        int maximumFlow = 0;
        Node sourceNode = g.getNode(source);
        for (int i = 0; i < sourceNode.getOutLeadingOrder(); i++) {
            maximumFlow += flow.get(sourceNode.getEdge(i));
            //  System.out.println("ooo: "+ sourceNode.getEdge(i).toString());
        }
        return maximumFlow;
    }

    static LinkedList<Edge> bfs(DirectedGraph g, Object start, Object target,
            HashMap<Edge, Integer> flow) {
        // The edge by which a node was reached.
        HashMap<Object, Edge> parent = new HashMap<>();
        // All outer nodes of the current search iteration.
        LinkedList<Object> fringe = new LinkedList<>();
        // We need to put the start node into those two.
        parent.put(start, null);
        fringe.add(start);
        // The actual algorithm
        all:
        while (!fringe.isEmpty()) {
            // This variable is needed to prevent the JVM from having a
            // concurrent modification
            LinkedList<Object> newFringe = new LinkedList<>();
            // Iterate through all nodes in the fringe.
            for (Object nodeID : fringe) {
                Node node = g.getNode(nodeID);
                // Iterate through all the edges of the node.
                for (int i = 0; i < node.getOutLeadingOrder(); i++) {
                    Edge e = node.getEdge(i);
                    // Only add the node if the flow can be changed in an out
                    // leading direction. Also break, if the target is reached.
                    if (e.getStart().equals(nodeID)
                            && !parent.containsKey(e.getTarget())
                            && flow.get(e) < e.getCapacity()) {
                        parent.put(e.getTarget(), e);
                        if (e.getTarget().equals(target)) {
                            break all;
                        }
                        newFringe.add(e.getTarget());
                    } else if (e.getTarget().equals(nodeID)
                            && !parent.containsKey(e.getStart())
                            && flow.get(e) > 0) {
                        parent.put(e.getStart(), e);
                        if (e.getStart().equals(target)) {
                            break all;
                        }
                        newFringe.add(e.getStart());
                    }
                }
            }
            // Replace the fringe by the new one.
            fringe = newFringe;
        }

        // Return null, if no path was found.
        if (fringe.isEmpty()) {
            return null;
        }
        // If a path was found, reconstruct it.
        Object node = target;
        LinkedList<Edge> path = new LinkedList<>();
        while (!node.equals(start)) {
            Edge e = parent.get(node);
            path.addFirst(e);
            if (e.getStart().equals(node)) {
                node = e.getTarget();
            } else {
                node = e.getStart();
            }
        }

        // Return the path.
        //System.out.println("LALA: "+path.toString());
        return path;
    }
}
