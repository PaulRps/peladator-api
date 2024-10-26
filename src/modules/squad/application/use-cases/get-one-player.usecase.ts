import {Inject, Injectable} from '@nestjs/common'
import {
  PLAYER_DATABASE_OUT_PORT,
  PlayerDatabaseOutPort
} from '../ports/out/player-database-out.port'
import {Player, PlayerId} from '../../domain/player'

@Injectable()
export class GetOnePlayer {
  constructor(
    @Inject(PLAYER_DATABASE_OUT_PORT)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(id: PlayerId): Promise<Player | null> {
    return this.playerOutPort.getOne(id)
  }
}