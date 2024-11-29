import {Inject, Injectable} from '@nestjs/common'
import {SquadDatabaseOutPort} from '../ports/out/squad-database-out.port'

@Injectable()
export class SquadExists {
  constructor(
    @Inject(SquadDatabaseOutPort.name)
    private readonly squadDatabaseOutPort: SquadDatabaseOutPort
  ) {}

  execute(squadId: string): Promise<boolean> {
    return this.squadDatabaseOutPort.exists(squadId)
  }
}
