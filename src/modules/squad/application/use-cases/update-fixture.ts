import {Inject, Injectable} from '@nestjs/common'
import {Fixture} from '../../domain/fixture'
import {FixtureDatabaseOutPort} from '../ports/out/fixture-database-out.port'

@Injectable()
export class UpdateFixture {
  constructor(
    @Inject(FixtureDatabaseOutPort.name)
    private readonly fixtureDatabaseOutPort: FixtureDatabaseOutPort
  ) {}

  execute(fixture: Fixture): Promise<void> {
    return this.fixtureDatabaseOutPort.update(fixture)
  }
}
