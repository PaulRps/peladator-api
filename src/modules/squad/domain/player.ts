import {SquadId} from './squad'

export type PlayerId = string
export class Player {
  id: PlayerId
  squadId: SquadId
  name: string
  position: string
  level: number

  constructor({
    id,
    name,
    position,
    level,
    squadId
  }: {
    id?: PlayerId
    name?: string
    position?: string
    level?: number
    squadId?: SquadId
  }={}) {
    this.id = id
    this.name = name
    this.position = position
    this.level = level
    this.squadId = squadId
  }
}
