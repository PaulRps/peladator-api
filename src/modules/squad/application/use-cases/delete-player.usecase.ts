import {Inject, Injectable} from '@nestjs/common'
import {PlayerId} from '../../domain/player'
import {PlayerDatabaseOutPort} from '../ports/out/player-database-out.port'

@Injectable()
export class DeletePlayer {
  constructor(
    @Inject(PlayerDatabaseOutPort.name)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(id: PlayerId): Promise<void> {
    return this.playerOutPort.delete(id)
  }
}
