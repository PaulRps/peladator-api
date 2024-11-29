import {Injectable} from '@nestjs/common'
import {PlayerDatabaseOutPort} from 'src/modules/squad/application/ports/out/player-database-out.port'
import {PlayerId, Player} from 'src/modules/squad/domain/models/player'
import {PlayerRepository} from 'src/modules/squad/infra/adapters/out/persistence/mongo/player.repository'
import {PlayerConverter} from './player.converter'

@Injectable()
export class PlayerMongoOutAdapter implements PlayerDatabaseOutPort {
  constructor(
    private readonly playerRepository: PlayerRepository,
    private readonly playerConverter: PlayerConverter
  ) {}

  getOne(id: PlayerId): Promise<Player | null> {
    return this.playerRepository
      .getOne(id)
      .then((player) => this.playerConverter.toPlayer(player))
  }

  getBy(squadId: string, ids?: string[]): Promise<Player[]> {
    return this.playerRepository
      .getBy(squadId, ids)
      .then((players) =>
        players?.map((player) => this.playerConverter.toPlayer(player))
      )
  }

  create(player: Player): Promise<PlayerId> {
    return this.playerRepository.save(
      this.playerConverter.toPlayerDocument(player)
    )
  }

  update(player: Player): Promise<void> {
    return this.playerRepository.update(
      this.playerConverter.toPlayerDocument(player)
    )
  }

  delete(id: PlayerId): Promise<void> {
    return this.playerRepository.delete(id)
  }

  savePlayerForFixture(player: Player): Promise<void> {
    return this.playerRepository.saveForFixture(
      this.playerConverter.toPlayerForFixtureDocument(player)
    )
  }

  getPlayersForFixture(squadId: string): Promise<Player[]> {
    return this.playerRepository
      .getPlayersForFixture(squadId)
      .then((players) =>
        players?.map((player) =>
          this.playerConverter.toPlayerForFixture(player)
        )
      )
  }
  existsPlayerForFixture(playerId: string, squadId: string): Promise<boolean> {
    return this.playerRepository.existsPlayerForFixture(playerId, squadId)
  }
}
