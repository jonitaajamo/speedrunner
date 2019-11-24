# Testing

Project is tested using Junit tests. Test coverage is reported using Jacoco coverage reports and code style is tested with checkstyle. 

### Test cases
Currently tests are implemented for Astar, Dijkstra and NodeList.

#### NodeListTest
All basic operations are tested with simple inputs. Iteration is tested with foreach loop.

#### Search algorithms
Astar and Dijkstra are tested with small ready made text maps. Two cases are tested: if goal is reachable or if it's not.

### Running tests

Junit tests can be run using command ```gradle test``` from java project root (speedrunner/speedrunner).
