import {Inject, Injectable} from '@nestjs/common'
import {Squad} from '../../domain/squad'
import {SquadDatabaseOutPort} from '../ports/out/squad-database-out.port'

@Injectable()
export class UpdateSquad {
  constructor(
    @Inject(SquadDatabaseOutPort.name)
    private readonly squadOutPort: SquadDatabaseOutPort
  ) {}

  execute(squad: Squad): Promise<void> {
    return this.squadOutPort.update(squad)
  }
}
