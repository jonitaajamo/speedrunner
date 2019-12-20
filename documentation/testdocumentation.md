# Testing

Project is tested using Junit tests. Test coverage is reported using Jacoco coverage reports and code style is tested with checkstyle. 

### Test cases
Currently tests are implemented for Astar, Dijkstra and NodeList.

#### NodeListTest
All basic operations are tested with simple inputs. Iteration is tested with foreach loop.

#### NodeQueueTest
Basic operations are tested: enqueue and dequeue.

#### NodePriorityQueue
Basic operations are tested and if multiple adds work with correct logic.

#### Search algorithms
Astar and Dijkstra are tested with small ready made text maps. Two cases are tested: if goal is reachable or if it's not.

### Running tests

Junit tests can be run using command ```gradle test``` from java project root (speedrunner/speedrunner).

## Benchmark testing

Programs benchmark test test average time to find the route, average node count and average path length.
Included benchmark were ran with Intel(R) Core(TM) i5-8250U CPU @ 1.60GHz processor.

### Time

   | Algorithm           |  1 iteration avg. | 100 iteration avg.| 1000 iteration avg.|
   | ------------------- | ------------------| ------------------| -------------------| 
   | A*                  | 4.0286 ms         | 0.3984 ms         | 0.1387 ms          | 
   | Dijkstra            | 8.0947 ms         | 0.5248 ms         | 0.4098 ms          | 
   | BFS                 | 4.6472 ms         | 0.7288 ms         | 0.5564 ms          |
   
### Visited nodes
Node count was unaffected when running multiple iterations.

 | Algorithm           |Node count on test map | 
 | ------------------- | ----------------------|  
 | A*                  | 887                   |  
 | Dijkstra            | 4911                  | 
 | BFS                 | 4914                  | 
 
 ### Path length
 Path length was unaffextes when running multiple iterations.
 
 | Algorithm           |Path length on test map| 
 | ------------------- | ----------------------|  
 | A*                  | 228                   |  
 | Dijkstra            | 220                   | 
 | BFS                 | 220                   |
 
 ## Findings
 * Time running any algorithm was shorter on each iteration
 
 I guess this has something to do with Java optimizing memory and CPU usage on each iteration. 
 

* Problems with A* heuristic

A* should probably find the same path as Dijkstra, but fails to do so. Possibly due to simple Manhattan heuristic.
