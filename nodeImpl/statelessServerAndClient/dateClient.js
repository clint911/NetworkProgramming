import net from "net";

const client = new net.Socket()
client.connect({ port: 59090, host: process.argv[2] ?? "localhost" })
client.on("data", (data) => {
  console.log(data.toString("utf-8"))
  })
