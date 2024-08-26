const EventEmitter = require("events");
const net = require("net");
const { parse, parseToRequest, setContentTypeHeader } = require("./parser");

class Server extends EventEmitter {
    constructor() {
        super();
        this.sockets = [];
        this.tcpServer = net.createServer();
        this.tcpServer.on("connection", this.handleConnection.bind(this));
    }

    handleConnection(socket) {
        this.sockets.push(socket);

        socket.on("data", (data) => {
            this.emit("request", parseToRequest(data.toString()));
        });

        socket.on("close", (hadError) => {
            this.emit("close", hadError);
        });

        socket.on("error", (error) => {
            this.emit("error", error);
        });
    }

    send(body) {
        this.sockets.forEach(socket => {
            socket.write(
                parse({
                    headers: {
                        contentType: setContentTypeHeader(body)
                    },
                    body: body,
                })
            );
        });
    }

    startServer(host, port, callback) {
        this.tcpServer.listen(port, host, () => {
            callback(this.tcpServer.address());
        });
    }
}
module.exports.Server = Server;
