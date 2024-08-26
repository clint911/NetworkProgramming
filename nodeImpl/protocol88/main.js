const { Client } = require("./client")
const { Server } = require("./server")

module.exports = {
    server: new Server(),
    connectToServer: function(host, port) {
        return new Client(host, port);
    }
}
