import {PlayerId} from './player'
import {SquadId} from './squad'

export class PlayerFixtureHistory {
  id: PlayerId
  squadId: SquadId
  name: string
  position: string
  level: number
  history: FixtureHistory[]

  constructor({
    id,
    squadId,
    name,
    position,
    level,
    history
  }: Partial<PlayerFixtureHistory> = {}) {
    this.id = id
    this.squadId = squadId
    this.name = name
    this.position = position
    this.level = level
    this.history = history
  }
}

export class FixtureHistory {
  fixtureId: string
  date: string

  constructor({fixtureId, date}: Partial<FixtureHistory> = {}) {
    this.fixtureId = fixtureId
    this.date = date
  }
}
