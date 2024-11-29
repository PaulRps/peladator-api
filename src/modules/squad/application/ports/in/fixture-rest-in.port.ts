import {Fixture} from 'src/modules/squad/domain/models/fixture'
import {FixtureCriteria} from 'src/modules/squad/domain/models/fixture-criteria'
import {SquadId} from 'src/modules/squad/domain/models/squad'

export interface FixtureRestInPort {
  createFixture(fixture: FixtureCriteria): Promise<Fixture>
  getLatestFixture(squadId: SquadId): Promise<Fixture>
  update(fixture: Fixture): Promise<void>
}
