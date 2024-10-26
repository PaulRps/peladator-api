import {Inject, Injectable} from '@nestjs/common'
import {
  PLAYER_DATABASE_OUT_PORT,
  PlayerDatabaseOutPort
} from '../ports/out/player-database-out.port'
import {PlayerId} from '../../domain/player'

@Injectable()
export class DeletePlayer {
  constructor(
    @Inject(PLAYER_DATABASE_OUT_PORT)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(id: PlayerId): Promise<void> {
    return this.playerOutPort.delete(id)
  }
}