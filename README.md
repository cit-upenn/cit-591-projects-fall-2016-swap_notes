# Swap Notes

## About
Swap Notes is an application that creates an abridged version of a PDF file based on user input. 


## Structure

####swap_notes
Java project folder that contains source code and external libraries.

####img
Contains images relevant to the project, instructions, etc.


## Instruction

- Import the swap_notes project folder into eclipse.
- Run GuiMain.java.
- Refer to manual.pdf for details.


## Design

Swap Notes is implemented with the following five classes.

1. GuiMain: Main class that takes user input and runs the program.
2. FileInputFilter: Takes a file as input and filters the pages that meets the keyword condition.
3. AnalyzedPage: A class that represents a single PDF page and its relevance score from vector space analysis or keyword frequency analysis. 
4. DocAnalyzer: Sorts and limits the number of pages for the final document output.
5. DocPrinter: Saves the output as a pdf file to a directory selected by the user.


## CRC

![alt tag](https://github.com/cit-upenn/cit-591-projects-fall-2016-swap_notes/blob/master/img/crc.png)


## Algorithm

We use an adjusted version of the Vector Space Model described in Chapter 14 of "Introduction to Information Retrieval." http://nlp.stanford.edu/IR-book/

In our model, each pdf page is a vector in an n-dimensional vector space, where n denotes the number of distinct words in the universe. The length and the direction of the vector represents the frequency of each word. In other words, a page is represented as an unordered collection of words. 

After obtaining the log-weighted normalized value for each vector, we use the cosine value (angular difference) between each vector to measure their similarity. In this case, the cosine is simply the dot product of the two vectors.

We use array lists to store the keywords and use hash maps to contain the score (cosine values).


## API

Apache PDFBox 
https://pdfbox.apache.org/

This API is used for loading, parsing, and saving files in pdf format.
We mainly used the PDDocument and PDPage classes to represent the pages as a Java object.
