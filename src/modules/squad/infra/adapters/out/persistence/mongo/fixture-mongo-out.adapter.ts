import {Injectable} from '@nestjs/common'
import {Types} from 'mongoose'
import {FixtureDatabaseOutPort} from 'src/modules/squad/application/ports/out/fixture-database-out.port'
import {Fixture} from 'src/modules/squad/domain/models/fixture'
import {SquadId} from 'src/modules/squad/domain/models/squad'
import {FixtureRepository} from 'src/modules/squad/infra/adapters/out/persistence/mongo/fixture.repository'
import {FixtureConverter} from './fixture.converter'
import {FilterFixture} from 'src/modules/squad/domain/models/filter-fixture'

@Injectable()
export class FixtureMongoOutAdapter implements FixtureDatabaseOutPort {
  constructor(
    private readonly fixtureRepository: FixtureRepository,
    private readonly fixtureConverter: FixtureConverter
  ) {}

  create(fixture: Fixture): Promise<Fixture> {
    return this.fixtureRepository
      .save(this.fixtureConverter.toFixtureDocument(fixture))
      .then((fixture) => this.fixtureConverter.toFixture(fixture))
  }

  getLatestFixture(squadId: SquadId): Promise<Fixture | null> {
    return this.fixtureRepository
      .getLatestFixture(new Types.ObjectId(squadId))
      .then((fixture) =>
        fixture ? this.fixtureConverter.toFixture(fixture) : null
      )
  }

  filterFixture(filter: FilterFixture): Promise<Fixture[]> {
    return this.fixtureRepository
      .filterFixtures(filter)
      .then((fixtures) =>
        fixtures?.map((fixture) => this.fixtureConverter.toFixture(fixture))
      )
  }

  update(fixture: Fixture): Promise<void> {
    return this.fixtureRepository.update(
      this.fixtureConverter.toFixtureDocument(fixture)
    )
  }

  delete(id: string): Promise<void> {
    return this.fixtureRepository.delete(id)
  }
}
