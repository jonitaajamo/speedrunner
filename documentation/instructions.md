

# Instructions

## Running

Project uses Gradle as its build system. 

Application has been created with [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). 
You can download `speedrunner.jar` from [GitHub-releases](https://github.com/jonitaajamo/speedrunner/releases).
Navigate to directory where you've downloaded the file and run command `java -jar speedrunner.jar`.

You can build and run the source code by cloning this repository and running `gradle run` or `./gradlew run` if you prefer that way.


## UI

After you've got the application running you are faced with glorious CLI. You have three option 1) Benchmarking, 2) Path finding or q/exit to quit.

### Benchmarking

With benchmarking, you can run selected algorithms, with selected number of iterations, to provide results about run time, visited nodes and path length. 
Currently you have to provide tested map as .map file and selected start and goal by hard coding it.

### Path finding

You can select option 2) for path finding on map. Resulted path will be saved to the same directory as .jar file or to project root, if you ran from source.

## Testing

Tests can be run using command ```gradle test```

Test coverage report can be generated using ```gradle test jacocoTestReport```

### Javadoc

JavaDoc can be generated using ```gradle javadoc```

### Checkstyle

Code style can be checked using ```gradle checkstyleMain```
