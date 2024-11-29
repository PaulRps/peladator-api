import {Inject, Injectable} from '@nestjs/common'
import {Fixture} from '../../domain/models/fixture'
import {FixtureDatabaseOutPort} from '../ports/out/fixture-database-out.port'

@Injectable()
export class DeleteFixture {
  constructor(
    @Inject(FixtureDatabaseOutPort.name)
    private readonly fixtureDatabaseOutPort: FixtureDatabaseOutPort
  ) {}

  execute(id: string): Promise<void> {
    return this.fixtureDatabaseOutPort.delete(id)
  }
}
