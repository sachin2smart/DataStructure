package in.sachinshinde.misc;

import java.util.*;

public class SkylineProblem {

	public List<List<Integer>> getSkyline(int[][] buildings) {
	    List<List<Integer>> result = new ArrayList<>();
	    List<int[]> startAndHeightListArray = new ArrayList<>();
	    for(int[] b:buildings) {
			startAndHeightListArray.add(new int[]{b[0], -b[2]});
			startAndHeightListArray.add(new int[]{b[1], b[2]});
	    }
	    startAndHeightListArray.sort((a, b) -> {
			if (a[0] != b[0]) {
				return a[0] - b[0];
			}
			return a[1] - b[1];
		});
	    Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
	    pq.offer(0);
	    int prevHeight = 0;
	    for(int[] startAndHeightArr:startAndHeightListArray) {
	        if(startAndHeightArr[1] < 0) {
	            pq.offer(-startAndHeightArr[1]);
	        }
			else {
	            pq.remove(startAndHeightArr[1]);
	        }
	        int currMaxHeight = pq.peek();
	        if(prevHeight != currMaxHeight) {
	            result.add(List.of(startAndHeightArr[0], currMaxHeight));
				prevHeight = currMaxHeight;
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
	            }
				else if (next < build.length && build[next][0] <= point[1]) {
	                if (build[next][2] > point[2]) {
	                    if (build[next][0] == point[0]) {
	                        result.remove(result.size() - 1);
	                    }
	                    if (build[next][1] <= point[1]) {
	                        queue.add(point);
	                    }
	                    point = build[next];
	                    result.add(List.of(point[0], point[2]));
	                }
					else if (build[next][1] > point[1]) {
	                    queue.add(build[next]);
	                }
	                next++;
	            }
				else {
	                int[] cur = queue.poll();
	                while (cur != null && cur[1] <= point[1]) {
	                    cur = queue.poll();
	                }
	                if (cur == null) {
	                    result.add(List.of(point[1], 0));
	                }
					else if (cur[2] < point[2]) {
	                    result.add(List.of(point[1], cur[2]));
	                }
	                point = cur;
	            }

	        }
	        return result;
	    }

	public static void main(String[] args) {
		SkylineProblem skylineProblem = new SkylineProblem();
		System.out.println(skylineProblem.getSkyline(new int[][]{
				{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}
		}));
	}
	public List<List<Integer>> getSkyline6(int[][] buildings) {
		// Sort the unique positions of all the edges.
		SortedSet<Integer> edgeSet = new TreeSet<Integer>();
		for (int[] building : buildings) {
			int left = building[0], right = building[1];
			edgeSet.add(left);
			edgeSet.add(right);
		}
		List<Integer> edges = new ArrayList<Integer>(edgeSet);

		// Hast table 'edgeIndexMap' record every {position : index} pairs in edges.
		Map<Integer, Integer> edgeIndexMap = new HashMap<>();
		for (int i = 0; i < edges.size(); ++i) {
			edgeIndexMap.put(edges.get(i), i);
		}

		// Initialize 'heights' to record maximum height at each index.
		int[] heights = new int[edges.size()];

		// Iterate over all the buildings.
		for (int[] building : buildings) {
			// For each building, find the indexes of its left
			// and right edges.
			int left = building[0], right = building[1], height = building[2];
			int leftIndex = edgeIndexMap.get(left), rightIndex = edgeIndexMap.get(right);

			// Update the maximum height within the range [left_idx, right_idx)
			for (int idx = leftIndex; idx < rightIndex; ++idx) {
				heights[idx] = Math.max(heights[idx], height);
			}
		}

		List<List<Integer>> answer = new ArrayList<>();

		// Iterate over 'heights'.
		for (int i = 0; i < heights.length; ++i) {
			int currHeight = heights[i], currPos = edges.get(i);

			// Add all the positions where the height changes to 'answer'.
			if (answer.isEmpty() || answer.get(answer.size() - 1).get(1) != currHeight) {
				answer.add(Arrays.asList(currPos, currHeight));
			}
		}
		return answer;
	}


	// Divide and conquer
	public List<List<Integer>> getSkyline7(int[][] buildings) {
		return divideAndConquer(buildings, 0, buildings.length - 1);
	}

	public List<List<Integer>> divideAndConquer(int[][] buildings, int left, int right) {
		// If the given array of building contains only 1 building, we can
		// directly return the corresponding skyline.
		if (left == right) {
			List<List<Integer>> answer = new ArrayList<>();
			answer.add(Arrays.asList(buildings[left][0], buildings[left][2]));
			answer.add(Arrays.asList(buildings[left][1], 0));
			return answer;
		}

		// Otherwise, we shall recursively divide the buildings and
		// merge the skylines. Cut the given skyline into two halves,
		// get skyline from each half and merge them into a single skyline.
		int mid = (right - left)/2 + left;
		List<List<Integer>> leftSkyline = divideAndConquer(buildings, left, mid);
		List<List<Integer>> rightSkyline = divideAndConquer(buildings, mid+1, right);

		return mergeSkylines(leftSkyline, rightSkyline);
	}

	// Given two skylines: leftSky and rightSky, merge them into one skyline.
	public List<List<Integer>> mergeSkylines(List<List<Integer>> leftSkyline, List<List<Integer>> rightSkyline) {
		// Initalize leftPos=0, rightPos=0 as the pointer of lft_sky and rgt_sky.
		// Since we start from the left ground, thus our current height curY = 0,
		// the previous height from lft_sky and rgt_sky are also 0.
		List<List<Integer>> answer = new ArrayList<>();
		int leftPos = 0, rightPos = 0;
		int leftPrevHeight = 0, rightPrevHeight = 0;
		int curX, curY;

		// Now we start to iterate over both skylines.
		while (leftPos < leftSkyline.size() && rightPos < rightSkyline.size()) {
			int nextLeftX = leftSkyline.get(leftPos).get(0);
			int nextRightX = rightSkyline.get(rightPos).get(0);

			// If we meet lft_sky key point first, our current height
			// changes to the larger one between height on left skyline
			// and the previous height on right skyline. Update the
			// previous height from lft_sky and increment leftPos by 1.
			if (nextLeftX < nextRightX) {
				leftPrevHeight = leftSkyline.get(leftPos).get(1);
				curX = nextLeftX;
				curY = Math.max(leftPrevHeight, rightPrevHeight);
				leftPos++;
			}

			// If we meet rgt_sky key point first, our current height
			// changes to the larger one between height on right skyline
			// and the previous height on left skyline. Update the
			// previous height from rgt_sky and increment rightPos by 1.
			else if (nextLeftX > nextRightX) {
				rightPrevHeight = rightSkyline.get(rightPos).get(1);
				curX = nextRightX;
				curY = Math.max(leftPrevHeight, rightPrevHeight);
				rightPos++;
			}

			// If both skyline key points has same x:
			// Our current height is the larger one, update the
			// previous height from lft_sky and rgt_sky.
			// Increment both leftPos and rightPos by 1.
			else {
				leftPrevHeight = leftSkyline.get(leftPos).get(1);
				rightPrevHeight = rightSkyline.get(rightPos).get(1);
				curX = nextLeftX;
				curY = Math.max(leftPrevHeight, rightPrevHeight);
				leftPos++;
				rightPos++;
			}

			// Discard those key points that has the same height
			// as the previous one.
			if (answer.isEmpty() || answer.get(answer.size()-1).get(1) != curY){
				answer.add(Arrays.asList(curX, curY));
			}
		}

		// If we finish iterating over any skyline,
		// just append the rest of the other skyline to the merged skyline.
		while(leftPos < leftSkyline.size()) {
			answer.add(leftSkyline.get(leftPos));
			leftPos++;
		}

		while(rightPos < rightSkyline.size()) {
			answer.add(rightSkyline.get(rightPos));
			rightPos++;
		}
		return answer;
	}
}
