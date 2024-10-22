import {Inject, Injectable} from '@nestjs/common'
import {Squad} from '../../domain/squad'
import {SQUAD_OUT_PORT, SquadOutPort} from '../ports/out/squad-out.port'

@Injectable()
export class UpdateSquad {
  constructor(
    @Inject(SQUAD_OUT_PORT) private readonly squadOutPort: SquadOutPort
  ) {}

  execute(squad: Squad): Promise<void> {
    return this.squadOutPort.update(squad)
  }
}
