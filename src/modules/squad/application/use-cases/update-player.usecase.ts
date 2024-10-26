import {Inject, Injectable} from '@nestjs/common'
import {Player} from '../../domain/player'
import {
  PLAYER_DATABASE_OUT_PORT,
  PlayerDatabaseOutPort
} from '../ports/out/player-database-out.port'

@Injectable()
export class UpdatePlayer {
  constructor(
    @Inject(PLAYER_DATABASE_OUT_PORT)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(player: Player): Promise<void> {
    return this.playerOutPort.update(player)
  }
}
