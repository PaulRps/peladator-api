import {Injectable} from '@nestjs/common'
import {Fixture} from '../../domain/fixture'
import {FixtureCriteria} from '../../domain/fixture-criteria'
import {CreateFixture} from '../use-cases/create-fixture'
import {GetLatestFixture as GetLatestFixture} from '../use-cases/get-latest-fixture'
import {UpdateFixture} from '../use-cases/update-fixture'

@Injectable()
export class FixtureRestService {
  constructor(
    private readonly creatFixture: CreateFixture,
    private readonly getLastFixtur: GetLatestFixture,
    private readonly updateFixture: UpdateFixture
  ) {}

  getLatestFixture(squadId: string): Promise<Fixture> {
    return this.getLastFixtur.execute(squadId)
  }

  createFixture(fixture: FixtureCriteria): Promise<Fixture> {
    return this.creatFixture.execute(fixture)
  }

  update(fixture: Fixture): Promise<void> {
    return this.updateFixture.execute(fixture)
  }
}
