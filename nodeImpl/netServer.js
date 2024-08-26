const net = require('node:net')

const client = net.createConnection(
    { port: 8124 },
    () => {
        console.log('connected to server')
        client.write('world\r\n')
    }
)

client.on('data', (data) => {
    console.log(data.toString())
    client.end()
})

client.on('end', () => {
    console.log('disconnected from server')
})

//another example 
net.createConnection({
    port: 80,
    onread: {
        buffer: Buffer.alloc(4 * 1024),
        callback: function(nread, buf) {
            console.log(buf.toString('utf8', 0, nread))
        }
    }
})
