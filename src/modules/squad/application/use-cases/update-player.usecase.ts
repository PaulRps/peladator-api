import {Inject, Injectable} from '@nestjs/common'
import {Player} from '../../domain/models/player'
import {PlayerDatabaseOutPort} from '../ports/out/player-database-out.port'

@Injectable()
export class UpdatePlayer {
  constructor(
    @Inject(PlayerDatabaseOutPort.name)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(player: Player): Promise<void> {
    return this.playerOutPort.update(player)
  }
}
