import { Inject, Injectable } from '@nestjs/common';
import {Squad, SquadId} from '../../domain/models/squad'
import {SquadDatabaseOutPort} from '../ports/out/squad-database-out.port'

@Injectable()
export class CreateSquad {
  constructor(
    @Inject(SquadDatabaseOutPort.name)
    private readonly squadOutPort: SquadDatabaseOutPort
  ) {}
  execute(squad: Squad): Promise<SquadId> {
    return this.squadOutPort.create(squad)
  }
}
