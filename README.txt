*University of Strasbourg*  
*L3 Computer Science, 5th semester*  
  
## Project : [Edge detection](https://en.wikipedia.org/wiki/Edge_detection)  
Java GUI application, using Swing.  
A UML diagram can be found in rapport.pdf. The file also contains more explanations about our design choices and the algorithms we used.
  
### Compilation & Execution

From the main directory, compile with : 
javac filtres_contour/*.java -classpath commons-math3-3.6.1.jar  
and run with: 
java -cp commons-math3-3.6.1.jar:. filtres_contour/ApplicationFiltres  
The user can choose any image file, run a filter and see the result on the application. The resulting image can be saved.

### Filters

Several filters are implemented to detect edges, namely Sobel, Roberts cross, Prewitt and Laplace.  
The remaining filters, such as the median filter, prepare the image before
applying any of the edge detectors.  

### Vectorisation

A bonus part of the project was to vectorise a black-and-white image. Our attempts to tackle this with regression lines does not yield interesting results and thus remains a to-do.
