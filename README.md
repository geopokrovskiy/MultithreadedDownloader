# MultithreadedDownloader
A multithreaded downloader using a byte buffer

/**
     * Project by theme
     * "Multithreaded Java Programming"
     * Exercise:
     * 1. Create a ProgressChecker interface with an implementation of the check method,
     * which takes two parameters as input: remain, total of data type long, does not return a result
     * 2. Implement the interface in the form of your own class, define the method
     * as in the application to the task. Produces a file download percentage calculation and output
     * to the information screen about this percentage
     * 3. Transfer the file download code from the server to a separate repository. As a field
     * class to make an object of type URL. The constructor takes as input a reference to a resource
     * as a string, as well as a variable of type ProgressChecker. In the load method of the repository, produce
     * downloading data from the server, as shown in the attachment to the task. File name of the output file
     * as a method argument. After reading each block of data, call the check method on the object
     * progress checker. Thus, you will see the download status of the file after each reading of its part.
     * 4. Create a service LoaderService. The constructor takes as input a reference to a resource,
     * file name where to save and file name where to write statistics
     * 5. In the load method of the service, create 2 parallel threads. The first thread should create
     * repository object and download data from server to file and second thread should time
     * record statistics in 10 seconds: current date and time until the first thread completes its work
     * 6. The service must wait for all threads to complete and return the result to the main method
     * true, which will need to be displayed there
     * @param arg
     */
