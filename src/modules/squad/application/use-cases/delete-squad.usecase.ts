import {Inject, Injectable} from '@nestjs/common'
import {
  SQUAD_DATABASE_OUT_PORT,
  SquadDatabaseOutPort
} from '../ports/out/squad-database-out.port'
import {SquadId} from '../../domain/squad'

@Injectable()
export class DeleteSquad {
  constructor(
    @Inject(SQUAD_DATABASE_OUT_PORT)
    private readonly squadOutPort: SquadDatabaseOutPort
  ) {}
  execute(id: SquadId): Promise<void> {
    return this.squadOutPort.delete(id)
  }
}
