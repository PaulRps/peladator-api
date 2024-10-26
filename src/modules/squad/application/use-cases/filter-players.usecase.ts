import { Inject, Injectable, Logger } from '@nestjs/common'
import { Player } from '../../domain/player'
import {
  PLAYER_DATABASE_OUT_PORT,
  PlayerDatabaseOutPort
} from '../ports/out/player-database-out.port'

@Injectable()
export class FilterPlayers {
  constructor(
    @Inject(PLAYER_DATABASE_OUT_PORT)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(squadId: string): Promise<Player[]> {
    Logger.log(`Filtering players for squad ${squadId}`, FilterPlayers.name)
    return this.playerOutPort.getBy(squadId)
  }
}
