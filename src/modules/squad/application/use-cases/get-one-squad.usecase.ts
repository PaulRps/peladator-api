import {Inject, Injectable} from '@nestjs/common'
import {Squad, SquadId} from '../../domain/squad'
import {SQUAD_OUT_PORT, SquadOutPort} from '../ports/out/squad-out.port'

@Injectable()
export class GetOneSquad {
  constructor(
    @Inject(SQUAD_OUT_PORT) private readonly squadOutPort: SquadOutPort
  ) {}

  execute(id: SquadId): Promise<Squad | null> {
    return this.squadOutPort.getOne(id)
  }
}
