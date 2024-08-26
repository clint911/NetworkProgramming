const EventEmitter = require("events");
const net = require("net");
const { parse, parseToRequest, setContentTypeHeader } = require("./parser")

class Client extends EventEmitter {
    constructor(host, port) {
        super();
        this.client = net.createConnection({ host: host, port: port });
        this.init();
    }
    init() {
        this.client.on("data", (data) => {
            this.emit("response", parseToRequest(data.toString()));
        });
        this.client.on("error", (error) => {
            this.emit("error", error);
        });

        this.client.on("end", () => {
            this.emit("end");
        });
    }
    send(body) {
        this.client.write(
            parse({
                headers: {
                    contentType: setContentTypeHeader(body)
                },
                body: body,
            })
        );
    }
}

module.exports.Client = Client;
