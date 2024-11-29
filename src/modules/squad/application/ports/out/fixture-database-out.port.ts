import {FilterFixture} from 'src/modules/squad/domain/models/filter-fixture'
import {Fixture} from 'src/modules/squad/domain/models/fixture'
import {SquadId} from 'src/modules/squad/domain/models/squad'

export interface FixtureDatabaseOutPort {
  create(fixture: Fixture): Promise<Fixture>
  getLatestFixture(squadId: SquadId): Promise<Fixture | null>
  filterFixture(filter: FilterFixture): Promise<Fixture[]>
  update(fixture: Fixture): Promise<void>
  delete(id: string): Promise<void>
}

export namespace FixtureDatabaseOutPort {
  export const name = 'FixtureDatabaseOutPort'
}
