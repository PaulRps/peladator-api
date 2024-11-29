import {Injectable} from '@nestjs/common'
import {Player, PlayerId} from '../../domain/models/player'
import {CreatePlayer} from '../use-cases/create-player.usecase'
import {DeletePlayer} from '../use-cases/delete-player.usecase'
import {
  FilterPlayers,
  FilterPlayersForFixture
} from '../use-cases/filter-players.usecase'
import {GetOnePlayer} from '../use-cases/get-one-player.usecase'
import {UpdatePlayer} from '../use-cases/update-player.usecase'
import {GetPlayerPositions} from '../use-cases/get-player-positions.usecase'
import {GetPlayersForFixture} from '../use-cases/get-players-for-fixture.usecase'
import {SavePlayerForFixture} from '../use-cases/save-player-for-fixture.usecase'
import {PlayerFixtureHistory} from '../../domain/models/player-fixture-history'
import {GetPlayersFixtureHistory} from '../use-cases/get-players-fixture-history.usecase'

@Injectable()
export class PlayerRestService {
  constructor(
    private readonly createPlayer: CreatePlayer,
    private readonly updatePlayer: UpdatePlayer,
    private readonly getOnePlayer: GetOnePlayer,
    private readonly deletePlayer: DeletePlayer,
    private readonly filterPlayers: FilterPlayers,
    private readonly getPlayerPosition: GetPlayerPositions,
    private readonly savePlayerForFixtur: SavePlayerForFixture,
    private readonly getPlayersForFixtur: GetPlayersForFixture,
    private readonly getPlayersFixtureHistory: GetPlayersFixtureHistory
  ) {}

  create(player: Player): Promise<Player> {
    return this.createPlayer.execute(player)
  }

  delete(id: PlayerId): Promise<void> {
    return this.deletePlayer.execute(id)
  }

  update(player: Player): Promise<void> {
    return this.updatePlayer.execute(player)
  }

  getOne(id: PlayerId): Promise<Player | null> {
    return this.getOnePlayer.execute(id)
  }

  getBy(squadId: string, ids?: PlayerId[]): Promise<Player[]> {
    return this.filterPlayers.execute(
      new FilterPlayersForFixture({squadId, ids})
    )
  }

  getFixtureHistory(
    playerIds: string[],
    squadId: string
  ): Promise<PlayerFixtureHistory[]> {
    return this.getPlayersFixtureHistory.execute(playerIds, squadId)
  }

  getPlayerPositions(): Promise<string[]> {
    return this.getPlayerPosition.execute()
  }

  savePlayerForFixture(player: Player): Promise<void> {
    return this.savePlayerForFixtur.execute(player)
  }

  getPlayersForFixture(squadId: string): Promise<Player[]> {
    return this.getPlayersForFixtur.execute(squadId)
  }
}
