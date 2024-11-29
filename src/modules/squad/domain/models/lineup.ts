import {PlayerForFixture} from './player-for-fixture'

export class LineUp {
  name: string
  level: number
  players: PlayerForFixture[]

  constructor({
    name,
    level,
    players
  }: {name?: string; level?: number; players?: PlayerForFixture[]} = {}) {
    this.name = name
    this.level = level
    this.players = players
  }
}
