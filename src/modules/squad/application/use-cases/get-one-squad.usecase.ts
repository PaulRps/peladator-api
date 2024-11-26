import {Inject, Injectable} from '@nestjs/common'
import {Squad, SquadId} from '../../domain/squad'
import {SquadDatabaseOutPort} from '../ports/out/squad-database-out.port'

@Injectable()
export class GetOneSquad {
  constructor(
    @Inject(SquadDatabaseOutPort.name)
    private readonly squadOutPort: SquadDatabaseOutPort
  ) {}

  execute(id: SquadId): Promise<Squad | null> {
    return this.squadOutPort.getOne(id)
  }
}
