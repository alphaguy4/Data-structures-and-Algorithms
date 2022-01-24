package com.ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Non DFS approach
* Using dependent nodes
* */
public class TopologicalSort2 {


    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        JobGraph jobGraph = createJobGraph(jobs, deps);
        return getOrderedJobs(jobGraph);
    }

    public static JobGraph createJobGraph(List<Integer> jobs, List<Integer[]> deps){
        JobGraph jobGraph = new JobGraph(jobs);
        for(Integer[] dep: deps) {
            jobGraph.addDep(dep[0], dep[1]);
        }
        return jobGraph;
    }

    public static List<Integer> getOrderedJobs(JobGraph graph){
        List<Integer> orderedJobs = new ArrayList<Integer>();
        List<JobNode> nodesWithNoPrereqs = new ArrayList<JobNode>();

        for(JobNode node: graph.nodes) {
            if(node.numberOfPrereqs == 0) {
                nodesWithNoPrereqs.add(node);
            }
        }

        while(nodesWithNoPrereqs.size() > 0) {
            JobNode node = nodesWithNoPrereqs.get(nodesWithNoPrereqs.size() - 1);
            nodesWithNoPrereqs.remove(nodesWithNoPrereqs.size() - 1);
            orderedJobs.add(node.job);
            removeDeps(node, nodesWithNoPrereqs);
        }

        boolean containsCycle = false;
        for(JobNode node: graph.nodes) {
            if(node.numberOfPrereqs != 0) {
                containsCycle = true;
                break;
            }
        }

        return containsCycle ? new ArrayList<Integer>(): orderedJobs;
    }

    private static void removeDeps(JobNode node, List<JobNode> nodesWithNoPrereqs) {
        while(node.deps.size() > 0) {
            JobNode depNode = node.deps.get(node.deps.size() - 1);
            node.deps.remove(node.deps.size() - 1);
            depNode.numberOfPrereqs--;
            if(depNode.numberOfPrereqs == 0) {
                nodesWithNoPrereqs.add(depNode);
            }
        }
    }

    static class JobNode {
        public int job;
        public List<JobNode> deps;
        int numberOfPrereqs = 0;

        public JobNode(int job) {
            this.job = job;
            deps = new ArrayList<JobNode>();
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

        private void addDep(int job, int dep) {
            JobNode jobNode = getNode(job);
            JobNode depNode = getNode(dep);
            jobNode.deps.add(depNode);
            depNode.numberOfPrereqs++;
        }

        private void addNode(int job) {
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
    }
}
