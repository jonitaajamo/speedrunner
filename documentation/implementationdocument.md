# Implementation

The project's main data structure is priorityqueue, which is implemented using binary tree. 
A-star is implemented using Manhattan heuristic: ```Math.abs(x - this.goal.getX()) + Math.abs(y - this.goal.getY())```. 
Djisktra is the same implementation, as A-star, but there is no heuristic. Instead distance from start node is calculated
during each search iteration. 

## UI
Includes simple CLI ui. Usage is described in [instructions](https://github.com/jonitaajamo/speedrunner/blob/master/documentation/instructions.md).


## Time and space complexities


### Tietorakenteet
Three own data structures has been implemented to accept 'Node'-objects. 

* PriorityQueue using binary heap.

|                         |  Avg. case             | Worst case   |
| ----------------------- | ---------------------  | ------------ |
| Space complexity       | O(n)                   | O(n)         |
| Time complexity (add)   | O(log n)               | O(log n)     |
| Time complexity (remove)| O(log n)               | O(log n)     |

* Queue

|                         |  Avg. case             | Worst case   |
| ----------------------- | ---------------------  | ------------ |
| Space complexity        | O(n)                   | O(n)         |
| Time complexity (add)   | O(1)                   | O(1)         |
| Time complexity (remove)| O(1)                   | O(1)         |

* Queue

|                         |  Avg. case             | Worst case   |
| ----------------------- | ---------------------  | ------------ |
| Space complexity        | O(n)                   | O(n)         |
| Time complexity (add)   | O(n)                   | O(n)         |
| Time complexity (remove)| O(n)                   | O(n)         |

### Search algorithms

* A* = O(log h*(x)), where h is time used for calculating heuristic
* BFS = O(V + E), where V is numbe of vertices and E number of edges
* Dijkstra = O(n + mlogn)

## Benchmarking

Information about benchmarking is in [test documentation](https://github.com/jonitaajamo/speedrunner/blob/master/documentation/testdocumentation.md)

## Suggestions for improvements

GUI would make user experience better. Testing on multiple maps and randomizing start point on each iteration would provide more interesting results. Making all data structures accept any object would be better. Implementing multiple heuristics for Astar would provide better results. 

## Sources

* [Array](https://en.wikipedia.org/wiki/Array_data_structure)
* [Queue](https://en.wikipedia.org/wiki/Queue_(abstract_data_type))
* [PriorityQueue](https://en.wikipedia.org/wiki/Priority_queue)
* [Astar](https://en.wikipedia.org/wiki/A*_search_algorithm)
* [BFS](https://en.wikipedia.org/wiki/Breadth-first_search)
* [Dijkstra](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)


