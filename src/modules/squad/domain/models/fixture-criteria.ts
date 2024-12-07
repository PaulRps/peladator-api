import {PlayerForFixture} from './player-for-fixture'
import {SquadId} from './squad'

export class FixtureCriteria {
  squadId: SquadId
  amountPlayersInLineUp: number
  players: PlayerForFixture[]
  priorityPlayers: PlayerForFixture[]

  constructor({
    squadId,
    amountPlayersInLineUp,
    players = [],
    priorityPlayers = []
  }: Partial<FixtureCriteria> = {}) {
    this.squadId = squadId
    this.amountPlayersInLineUp = amountPlayersInLineUp
    this.players = players
    this.priorityPlayers = priorityPlayers
  }
}
