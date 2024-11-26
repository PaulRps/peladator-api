import {Inject, Injectable} from '@nestjs/common'
import {Player} from '../../domain/player'
import {PlayerDatabaseOutPort} from '../ports/out/player-database-out.port'

@Injectable()
export class GetPlayersForFixture {
  constructor(
    @Inject(PlayerDatabaseOutPort.name)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(squadId: string): Promise<Player[]> {
    return this.playerOutPort.getPlayersForFixture(squadId)
  }
}
