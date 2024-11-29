import {Inject, Injectable} from '@nestjs/common'
import {SquadId} from '../../domain/models/squad'
import {SquadDatabaseOutPort} from '../ports/out/squad-database-out.port'

@Injectable()
export class DeleteSquad {
  constructor(
    @Inject(SquadDatabaseOutPort.name)
    private readonly squadOutPort: SquadDatabaseOutPort
  ) {}
  execute(id: SquadId): Promise<void> {
    return this.squadOutPort.delete(id)
  }
}
