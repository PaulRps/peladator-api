import { Inject, Injectable, Logger } from '@nestjs/common'
import { Player } from '../../domain/player'
import { PLAYER_OUT_PORT, PlayerOutPort } from '../ports/out/player-out.port'

@Injectable()
export class FilterPlayers {
  constructor(
    @Inject(PLAYER_OUT_PORT) private readonly playerOutPort: PlayerOutPort
  ) {}

  execute(squadId: string): Promise<Player[]> {
    Logger.log(`Filtering players for squad ${squadId}`, FilterPlayers.name)
    return this.playerOutPort.getBy(squadId)
  }
}
