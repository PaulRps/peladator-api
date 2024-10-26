import {PlayerForFixture} from './player-for-fixture'

export class LineUp {
  name: string
  players: PlayerForFixture[]

  constructor({
    name,
    players
  }: {name?: string; players?: PlayerForFixture[]} = {}) {
    this.name = name
    this.players = players
  }
}
