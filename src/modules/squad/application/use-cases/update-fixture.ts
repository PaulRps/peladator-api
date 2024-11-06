import {Inject, Injectable} from '@nestjs/common'
import {Fixture} from '../../domain/fixture'
import {
  FIXTURE_DATABASE_OUT_PORT,
  FixtureDatabaseOutPort
} from '../ports/out/fixture-database-out.port'

@Injectable()
export class UpdateFixture {
  constructor(
    @Inject(FIXTURE_DATABASE_OUT_PORT)
    private readonly fixtureDatabaseOutPort: FixtureDatabaseOutPort
  ) {}

  execute(fixture: Fixture): Promise<void> {
    return this.fixtureDatabaseOutPort.update(fixture)
  }
}
