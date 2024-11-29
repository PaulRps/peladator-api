import {SquadId} from './squad'
import {LineUp} from './lineup'

export class Fixture {
  id: string
  squadId: SquadId
  createdAt: string
  lineUps: LineUp[]

  constructor({
    id,
    squadId,
    createdAt: date,
    lineUps
  }: {
    id?: string
    squadId?: SquadId
    createdAt?: string
    lineUps?: LineUp[]
  } = {}) {
    this.id = id
    this.squadId = squadId
    this.createdAt = date
    this.lineUps = lineUps
  }
}
