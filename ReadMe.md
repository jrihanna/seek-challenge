# Getting Started

### Reference Documentation
As a solution for this challenge, I decided to create an API just because it was easier.

The API has:

* A Controller where you can call the "parser" resource to get the response
* A Service where the logic can be found


For each output that was requested in the pdf, I created a public method just so that it would be possible to call them one by one. A unit test for each of them has also been added.

This solution is not optimised as I didn't spend much time doing so. If I were to optimise it, I would find a way to combine some of the processes so that I wouldn't need to bruteforce the list multiple time.

To test this app, simply run it in a local environment and call localhost:8080/parser from Postman.

