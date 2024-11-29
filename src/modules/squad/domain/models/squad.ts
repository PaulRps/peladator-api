import {Player} from './player'

export type SquadId = string
export class Squad {
  id: SquadId
  name: string
  players?: Player[]

  constructor({
    id,
    name,
    players
  }: {id?: string; name?: string; players?: Player[]} = {}) {
    this.name = name
    this.players = players
    this.id = id
  }
}
