import {Inject, Injectable} from '@nestjs/common'
import {Squad, SquadId} from '../../domain/squad'
import {
  SQUAD_DATABASE_OUT_PORT,
  SquadDatabaseOutPort
} from '../ports/out/squad-database-out.port'

@Injectable()
export class GetOneSquad {
  constructor(
    @Inject(SQUAD_DATABASE_OUT_PORT)
    private readonly squadOutPort: SquadDatabaseOutPort
  ) {}

  execute(id: SquadId): Promise<Squad | null> {
    return this.squadOutPort.getOne(id)
  }
}
