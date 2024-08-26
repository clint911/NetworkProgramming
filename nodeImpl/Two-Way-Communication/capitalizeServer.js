import net from "net";

const server = net.createServer((socket) => {
  console.log("Connection from", socket.remoteAddress, "port", socket.remotePort)

  socket.on("data", (buffer) => {
    console.log("Request from", socket.remoteAddress, "port", socket.remotePort)
    socket.write(`${buffer.toString("utf-8").toUpperCase()}\n`)
    })
    })
server.maxConnections = 20
server.listen(59898)
