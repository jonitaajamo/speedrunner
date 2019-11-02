# Project spesifications

In this project the main goal is to find shortest path on Dragon Age 2 map as fast as possible. 
I chose this topic because of my new found love for search algorithms and good availability for interesting data sets.

## Data structures and aglorithms used

* I plan to use 2-4 different algorithms:
  * A-star
  * BFS
  * Djisktra
  * Brute force, if its not too intensive to compute
  
* Required data structures:
  * Priority queue (multiple possible implementations)
  * Array to store the map
  
## Inputs for the program

* Map as .map file
* Start and goal(s) as user input

These inputs are used to calculate the shortest path in the map between the start node and goal node. 
If I have time I try to implement finding path through multiple nodes.

Sample map from Moving AI labs as png:

<img src="https://www.movingai.com/benchmarks/da2/ca_cave.png" data-canonical-src="https://www.movingai.com/benchmarks/da2/ca_cave.png" width="250" />

Part of the map will look something like this as .map file:

....TTTT@@@@TTTTTT@@@@T</br>
....TTTT@@@@TTTTTT@@@@T</br>
...TTTTT@@@@TTTTTT@@@@T
  
## Target time and space complexity

* Time complexity for saving the map to array has time and space complexity of O(n) and O(1), when accessing.
* A-star's time complexity is dependant on the implemented heuristic: O(log h*(x)), where h is the heuristic. 
  I will try to implement a couple different heuristics and see which one is the best. 
* BFS has time complexity of O(E + V), where E is number of edges and V number of vertices. 
  It is also dependant on the implemented datastructure 
  
  
  

### Sources
* https://www.movingai.com/benchmarks/ (maps)
* https://en.wikipedia.org/wiki/A*_search_algorithm
