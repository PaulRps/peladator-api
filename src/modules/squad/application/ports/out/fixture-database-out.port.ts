import {Fixture} from 'src/modules/squad/domain/fixture'
import {SquadId} from 'src/modules/squad/domain/squad'

export interface FixtureDatabaseOutPort {
  create(fixture: Fixture): Promise<Fixture>
  getLatestFixture(squadId: SquadId): Promise<Fixture | null>
  update(fixture: Fixture): Promise<void>
}

export namespace FixtureDatabaseOutPort {
  export const name = 'FixtureDatabaseOutPort'
}
