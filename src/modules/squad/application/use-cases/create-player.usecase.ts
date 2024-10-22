import {Inject, Injectable} from '@nestjs/common'
import {PLAYER_OUT_PORT, PlayerOutPort} from '../ports/out/player-out.port'
import {Player, PlayerId} from '../../domain/player'

@Injectable()
export class CreatePlayer {
  constructor(
    @Inject(PLAYER_OUT_PORT) private readonly playerOutPort: PlayerOutPort
  ) {}

  execute(player: Player): Promise<PlayerId> {
    return this.playerOutPort.create(player)
  }
}
