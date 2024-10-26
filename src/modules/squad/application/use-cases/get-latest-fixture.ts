import {Inject, Injectable} from '@nestjs/common'
import {
  FIXTURE_DATABASE_OUT_PORT,
  FixtureDatabaseOutPort
} from '../ports/out/fixture-database-out.port'
import {Fixture} from '../../domain/fixture'

@Injectable()
export class GetLatestFixture {
  constructor(
    @Inject(FIXTURE_DATABASE_OUT_PORT)
    private readonly fixtureDatabaseOutPort: FixtureDatabaseOutPort
  ) {}

  execute(squadId: string): Promise<Fixture | null> {
    return this.fixtureDatabaseOutPort.getLatestFixture(squadId)
  }
}
