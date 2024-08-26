<h1>Revisiting Network Programming in Java </h1>
<h2>Sample Applications in Order of Increasing Complexity </h2>
<li> Trivial Date Server & Client: Illustrating a simple one way communication. Server sends Data to the client only.  </li>
It Simply listens on a port & when client connects, server sends the current datetime to the client.  
<li>A Capitalize Server & Client: Illustrating two-way communication & server-side threads to more efficiently handle input communications simultaneously.</li>  
<li>A Two-player tic tac toe: Illustrating a server that needs to keep track of the state of a game & inform each client of it, so they can each update their own displays. </li>  
<li> A multi-user chat application: A server must broadcast messages to all its clients</li>
#All these projects will be in the TCP package/directory since we are going to use TCP exclusively. 

