db = new Mongo().getDB('peladator')
db.createUser({
  user: 'admin',
  pwd: 'mysecretpassword',
  roles: [{role: 'readWrite', db: 'peladator'}]
})

console.log('PELADATOR DB CREATED')

db.createCollection('players')
let players = []
let total = 10
const squadIdsMap = {}
squadIdsMap[1] = {id: new ObjectId(1), playerIds: []}
squadIdsMap[2] = {id: new ObjectId(2), playerIds: []}
for (let i = 0; i < total; i++) {
  let playerId = new ObjectId(i+1)
  let sqId = 1
  if (i % 2 === 0) {
    squadIdsMap[2].playerIds.push(playerId)
    sqId = squadIdsMap[2].id
  } else {
    squadIdsMap[1].playerIds.push(playerId)
    sqId = squadIdsMap[1].id
  }
  players.push({
    _id: playerId,
    squadId: sqId,
    name: 'player-' + (i+1),
    position: 'ata',
    level: 5
  })
}

console.log('inserting ' + players.length + ' player documents on database')
db.players.insertMany(players)

db.createCollection('squads')
let squads = []
total = 2
for (let i = 0; i < total; i++) {
  squads.push({
    _id: squadIdsMap[i+1].id,
    name: 'Squad-' + (i+1),
    players: squadIdsMap[i+1].playerIds
  })
}

console.log('inserting ' + squads.length + ' squads documents on database')
db.squads.insertMany(squads)
