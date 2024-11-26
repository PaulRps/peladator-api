import {Inject, Injectable, Logger} from '@nestjs/common'
import {Player} from '../../domain/player'
import {PlayerDatabaseOutPort} from '../ports/out/player-database-out.port'

@Injectable()
export class FilterPlayers {
  constructor(
    @Inject(PlayerDatabaseOutPort.name)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  execute(filter: FilterPlayersForFixture): Promise<Player[]> {
    Logger.log(
      `Filtering players for squad ${filter.squadId}`,
      FilterPlayers.name
    )
    return this.playerOutPort.getBy(filter.squadId, filter.ids)
  }
}

export class FilterPlayersForFixture {
  ids?: string[]
  squadId: string
  constructor({ids, squadId}: {ids?: string[]; squadId: string}) {
    this.ids = ids
    this.squadId = squadId
  }
}
