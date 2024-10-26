import {PlayerForFixture} from './player-for-fixture'
import {SquadId} from './squad'

export class FixtureCriteria {
  squadId: SquadId
  amountPlayersInLineUp: number
  players: PlayerForFixture[]

  constructor({
    squadId,
    amountPlayersInLineUp,
    players
  }: {
    squadId?: SquadId
    amountPlayersInLineUp?: number
    players?: PlayerForFixture[]
  } = {}) {
    this.squadId = squadId
    this.amountPlayersInLineUp = amountPlayersInLineUp
    this.players = players
  }
}
