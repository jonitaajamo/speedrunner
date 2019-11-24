# Implementation

The project's main data structure is priorityqueue, which is implemented using binary tree. 
A-star is implemented using Manhattan heuristic: ```Math.abs(x - this.goal.getX()) + Math.abs(y - this.goal.getY())```. 
Djisktra is the same implementation, as A-star, but there is no heuristic. Instead distance from start node is calculated
during each search iteration. 

### UI
CLI ui will be implemented at a later date.
