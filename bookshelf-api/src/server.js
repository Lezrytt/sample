<<<<<<< HEAD
const Hapi = require('@hapi/hapi')
const routes = require('./routes')

const init = async () => {
  const server = Hapi.server({
    port: 5000,
    host: 'localhost',
    routes: {
      cors: {
        origin: ['*'] // cross origin resource sharing (cors) for all routes enabled
      }
    }
  })
  server.route(routes)
  await server.start()
  console.log(`Server is running at ${server.info.uri}`)
}

init()
=======
const Hapi = require('@hapi/hapi')
const routes = require('./routes')

const init = async () => {
  const server = Hapi.server({
    port: 5000,
    host: 'localhost',
    routes: {
      cors: {
        origin: ['*'] // cross origin resource sharing (cors) for all routes enabled
      }
    }
  })
  server.route(routes)
  await server.start()
  console.log(`Server is running at ${server.info.uri}`)
}

init()
>>>>>>> 3dc3a5eb4e2ce7fd0a847a5e503f9c044b7b6892
