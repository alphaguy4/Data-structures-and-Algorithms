package com.ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* This solution is based on a prereqs lists
* */
public class TopologicalSort {

    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        JobGraph jobGraph = createJobGraph(jobs, deps);
        return getOrderedJobs(jobGraph);
    }

    private static List<Integer> getOrderedJobs(JobGraph jobGraph) {
        List<Integer> orderedJobs = new ArrayList<Integer>();
        List<JobNode> nodes = jobGraph.nodes;

        while(nodes.size() > 0) {
            JobNode node = nodes.get(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);
            boolean containsCycle = depthFirstTraversal(node, orderedJobs);
            if(containsCycle) {
                return new ArrayList<Integer>();
            }
        }

        return orderedJobs;
    }

    private static boolean depthFirstTraversal(JobNode node, List<Integer> orderedJobs) {
        if(node.visited) {
            return false;
        }
        if(node.visiting) {
            return true;
        }

        node.visiting = true;
        for(JobNode prereq: node.prereqs) {
            boolean containsCycle = depthFirstTraversal(prereq, orderedJobs);
            if(containsCycle) {
                return true;
            }
        }
        node.visiting = false;
        node.visited = true;
        orderedJobs.add(node.job);
        return false;
    }


    private static JobGraph createJobGraph(List<Integer> jobs, List<Integer[]> deps) {
        JobGraph jobGraph = new JobGraph(jobs);
        for(Integer[] dep: deps) {
            jobGraph.addPrereq(dep[1], dep[0]);
        }
        return jobGraph;
    }

    static class JobNode {
        int job;
        List<JobNode> prereqs;
        boolean visited = false;
        boolean visiting = false;
        public JobNode(int value) {
            this.job = value;
            prereqs = new ArrayList<JobNode>();
        }
    }

    static class JobGraph {
        List<JobNode> nodes;
        Map<Integer, JobNode> graph;

        public JobGraph(List<Integer> jobs) {
            nodes = new ArrayList<JobNode>();
            graph = new HashMap<Integer, JobNode>();
            for(Integer job: jobs) {
                addNode(job);
            }
        }

        private void addNode(int job){
           JobNode newNode = new JobNode(job);
           nodes.add(newNode);
           graph.put(job, newNode);
        }

        private JobNode getNode(int job) {
            if(!graph.containsKey(job)) {
                graph.put(job, new JobNode(job));
            }
            return graph.get(job);
        }

        private void addPrereq(int job, int prereq) {
            JobNode jobNode = getNode(job);
            JobNode prereqNode = getNode(prereq);
            jobNode.prereqs.add(prereqNode);
        }
    }
}
