package in.sachinshinde.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class SkylineProblem {

	public List<int[]> getSkyline(int[][] buildings) {
	    List<int[]> result = new ArrayList<>();
	    List<int[]> height = new ArrayList<>();
	    for(int[] b:buildings) {
	        height.add(new int[]{b[0], -b[2]});
	        height.add(new int[]{b[1], b[2]});
	    }
	    Collections.sort(height, (a, b) -> {
	            if(a[0] != b[0]) 
	                return a[0] - b[0];
	            return a[1] - b[1];
	    });
	    Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
	    pq.offer(0);
	    int prev = 0;
	    for(int[] h:height) {
	        if(h[1] < 0) {
	            pq.offer(-h[1]);
	        } else {
	            pq.remove(h[1]);
	        }
	        int cur = pq.peek();
	        if(prev != cur) {
	            result.add(new int[]{h[0], cur});
	            prev = cur;
	        }
	    }
	    return result;
	}
	
	
	
	public List<List<Integer>> getSkyline2(int[][] buildings) {
	    List<List<Integer>> result = new ArrayList<>();
	    List<int[]> height = new ArrayList<>();
	    for(int[] b:buildings) {
	        height.add(new int[]{b[0], -b[2]});
	        height.add(new int[]{b[1], b[2]});
	    }
	    Collections.sort(height, (a, b) -> {
	            if(a[0] != b[0]) 
	                return a[0] - b[0];
	            return a[1] - b[1];
	    });
	    Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
	    pq.offer(0);
	    int prev = 0;
	    for(int[] h:height) {
	        if(h[1] < 0) {
	            pq.offer(-h[1]);
	        } else {
	            pq.remove(h[1]);
	        }
	        int cur = pq.peek();
	        if(prev != cur) {
	            result.add(new ArrayList<Integer>(Arrays.asList(h[0], cur)));
	            prev = cur;
	        }
	    }
	    return result;
	}
	
	  public List<List<Integer>> getSkyline3(int[][] buildings) {
	         //for all start and end of building put them into List of BuildingPoint
	        BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length*2];
	        int index = 0;
	        for(int building[] : buildings) {
	            buildingPoints[index] = new BuildingPoint();
	            buildingPoints[index].x = building[0];
	            buildingPoints[index].isStart = true;
	            buildingPoints[index].height = building[2];

	            buildingPoints[index + 1] = new BuildingPoint();
	            buildingPoints[index + 1].x = building[1];
	            buildingPoints[index + 1].isStart = false;
	            buildingPoints[index + 1].height = building[2];
	            index += 2;
	        }
	        Arrays.sort(buildingPoints);

	        //using TreeMap because it gives log time performance.
	        //PriorityQueue in java does not support remove(object) operation in log time.
	        TreeMap<Integer, Integer> queue = new TreeMap<>();
	        //PriorityQueue<Integer> queue1 = new PriorityQueue<>(Collections.reverseOrder());
	        queue.put(0, 1);
	        //queue1.add(0);
	        int prevMaxHeight = 0;
	        List<List<Integer>> result = new ArrayList<>();
	        for(BuildingPoint buildingPoint : buildingPoints) {
	            //if it is start of building then add the height to map. If height already exists then increment
	            //the value
	            if (buildingPoint.isStart) {
	                queue.compute(buildingPoint.height, (key, value) -> {
	                    if (value != null) {
	                        return value + 1;
	                    }
	                    return 1;
	                });
	              //  queue1.add(cp.height);
	            } else { //if it is end of building then decrement or remove the height from map.
	                queue.compute(buildingPoint.height, (key, value) -> {
	                    if (value == 1) {
	                        return null;
	                    }
	                    return value - 1;
	                });
	               // queue1.remove(cp.height);
	            }
	            //peek the current height after addition or removal of building x.
	            int currentMaxHeight = queue.lastKey();
	            //int currentMaxHeight = queue1.peek();
	            //if height changes from previous height then this building x becomes critcal x.
	            // So add it to the result.
	            if (prevMaxHeight != currentMaxHeight) {
	                result.add(Arrays.asList(buildingPoint.x, currentMaxHeight));
	                prevMaxHeight = currentMaxHeight;
	            }
	        }
	        return result;
	    }
	    
	     /**
	     * Represents either start or end of building
	     */
	    static class BuildingPoint implements Comparable<BuildingPoint> {
	        int x;
	        boolean isStart;
	        int height;

	        @Override
	        public int compareTo(BuildingPoint o) {
	            //first compare by x.
	            //If they are same then use this logic
	            //if two starts are compared then higher height building should be picked first
	            //if two ends are compared then lower height building should be picked first
	            //if one start and end is compared then start should appear before end
	            if (this.x != o.x) {
	                return this.x - o.x;
	            } else {
	                return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
	            }
	        }
	     }
	    
	    public List<List<Integer>> getSkyline4(int[][] buildings) {
	        List<List<Integer>> result = new ArrayList<>();
	        int[][] edges = new int[buildings.length * 2][];
	        for (int i=0; i<buildings.length; i++) {
	            edges[i * 2] = new int[] {i, 0}; // left edge
	            edges[i * 2 + 1] = new int[] {i, 1}; // right edge
	        }
	        Arrays.sort(edges, (p1, p2) -> {
	            int diff = buildings[p1[0]][p1[1]] - buildings[p2[0]][p2[1]];
	            if (diff != 0) {
	                return diff;
	            }
	            diff = p1[1] - p2[1]; // left edge first
	            if (diff != 0) {
	                return diff;
	            }
	            // both are left edges or both are right edges
	            if (p1[1] == 0) {
	                return buildings[p2[0]][2] -buildings[p1[0]][2]; // high to low
	            } else {
	                return buildings[p1[0]][2] - buildings[p2[0]][2]; // low to high
	            }
	        }); // sort all edges, open edge is before close edge
	        
	        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((b1, b2) -> buildings[b2][2] - buildings[b1][2]);
	        Set<Integer> removed = new HashSet<>();
	        for (int[] edge : edges) {
	            if (edge[1] == 0) { // left edge
	                maxHeap.add(edge[0]);
	            } else {
	                removed.add(edge[0]);
	                while (!maxHeap.isEmpty() && removed.contains(maxHeap.peek())) {
	                    maxHeap.poll();
	                }
	            }
	            int h = maxHeap.isEmpty() ? 0 : buildings[maxHeap.peek()][2];
	            if (result.isEmpty() || result.get(result.size() - 1).get(1) != h) {
	                result.add(Arrays.asList(buildings[edge[0]][edge[1]], h));
	            }
	        }
	        return result;
	    }
	    
	    public List<List<Integer>> getSkyline5(int[][] build) {
	        List<List<Integer>> result = new ArrayList<>();
	        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[2] - a[2]);

	        int next = 0;
	        int[] point = null;

	        while (point != null || next < build.length) {
	            if (point == null) {
	                point = build[next];
	                result.add(List.of(point[0], point[2]));
	                next++;
	            } else if (next < build.length && build[next][0] <= point[1]) {
	                if (build[next][2] > point[2]) {
	                    if (build[next][0] == point[0]) {
	                        result.remove(result.size() - 1);
	                    }
	                    if (build[next][1] <= point[1]) {
	                        queue.add(point);
	                    }
	                    point = build[next];
	                    result.add(List.of(point[0], point[2]));
	                } else if (build[next][1] > point[1]) {
	                    queue.add(build[next]);
	                }
	                next++;
	            } else {
	                int[] cur = queue.poll();
	                while (cur != null && cur[1] <= point[1]) {
	                    cur = queue.poll();
	                }
	                if (cur == null) {
	                    result.add(List.of(point[1], 0));
	                } else if (cur[2] < point[2]) {
	                    result.add(List.of(point[1], cur[2]));
	                }
	                point = cur;
	            }

	        }
	        return result;
	    }
	
}
