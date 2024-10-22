import {Inject, Injectable} from '@nestjs/common'
import {SQUAD_OUT_PORT, SquadOutPort} from '../ports/out/squad-out.port'
import {SquadId} from '../../domain/squad'

@Injectable()
export class DeleteSquad {
  constructor(
    @Inject(SQUAD_OUT_PORT) private readonly squadOutPort: SquadOutPort
  ) {}
  execute(id: SquadId): Promise<void> {
    return this.squadOutPort.delete(id)
  }
}
