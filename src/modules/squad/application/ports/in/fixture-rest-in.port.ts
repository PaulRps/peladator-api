import {Fixture} from 'src/modules/squad/domain/fixture'
import {FixtureCriteria} from 'src/modules/squad/domain/fixture-criteria'
import {SquadId} from 'src/modules/squad/domain/squad'

export interface FixtureRestInPort {
  createFixture(fixture: FixtureCriteria): Promise<Fixture>
  getLatestFixture(squadId: SquadId): Promise<Fixture>
  update(fixture: Fixture): Promise<void>
}
