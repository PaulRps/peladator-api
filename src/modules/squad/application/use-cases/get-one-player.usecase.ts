import {Inject, Injectable} from '@nestjs/common'
import {Player, PlayerId} from '../../domain/models/player'
import {PlayerDatabaseOutPort} from '../ports/out/player-database-out.port'

@Injectable()
export class GetOnePlayer {
  constructor(
    @Inject(PlayerDatabaseOutPort.name)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(id: PlayerId): Promise<Player | null> {
    return this.playerOutPort.getOne(id)
  }
}
