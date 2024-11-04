import {Inject, Injectable} from '@nestjs/common'
import {Player} from '../../domain/player'
import {
  PLAYER_DATABASE_OUT_PORT,
  PlayerDatabaseOutPort
} from '../ports/out/player-database-out.port'

@Injectable()
export class GetPlayersForFixture {
  constructor(
    @Inject(PLAYER_DATABASE_OUT_PORT)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(squadId: string): Promise<Player[]> {
    return this.playerOutPort.getPlayersForFixture(squadId)
  }
}
