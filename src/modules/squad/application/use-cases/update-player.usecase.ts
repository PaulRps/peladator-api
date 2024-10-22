import {Inject, Injectable} from '@nestjs/common'
import {PLAYER_OUT_PORT, PlayerOutPort} from '../ports/out/player-out.port'
import {PlayerId, Player} from '../../domain/player'

@Injectable()
export class UpdatePlayer {
  constructor(
    @Inject(PLAYER_OUT_PORT) private readonly playerOutPort: PlayerOutPort
  ) {}

  execute(player: Player): Promise<void> {
    return this.playerOutPort.update(player)
  }
}
