Our project allows the user to build mathematical functions and perform mathematical operations on them such as add, substruct, multiplys, organize the functions in a collection and is able to draw the functions.
the project has 4 main classes: Polynom, Monom, ComplexFunction and Function_GUI. 

Monom Class:
This class represents a simple "Monom" of shape a*x^b, where a real number and b is an none negative integer.

Polynom Class:
This class represents a Polynom of shape a_1x^b_1+a_2x^b_2+….+a_nx^b_n and consists of ArrayList of monoms.

Complex Function Class:
This class represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions), 
 y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).

Functions_GUI class:
This class holds a collection of functions and can write the functions to a file or read from a file and display them on a GUI window as a graph.
This class imports Gson jar in order to read and write to a json file.
