import {Inject, Injectable} from '@nestjs/common'
import {
  SQUAD_DATABASE_OUT_PORT,
  SquadDatabaseOutPort
} from '../ports/out/squad-database-out.port'

@Injectable()
export class SquadExists {
  constructor(
    @Inject(SQUAD_DATABASE_OUT_PORT)
    private readonly squadDatabaseOutPort: SquadDatabaseOutPort
  ) {}

  execute(squadId: string): Promise<boolean> {
    return this.squadDatabaseOutPort.exists(squadId)
  }
}
