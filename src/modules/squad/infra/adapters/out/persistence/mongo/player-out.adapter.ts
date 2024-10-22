import {Injectable} from '@nestjs/common'
import {PlayerOutPort} from 'src/modules/squad/application/ports/out/player-out.port'
import {PlayerId, Player} from 'src/modules/squad/domain/player'
import {PlayerRepository} from 'src/modules/squad/infra/config/persistence/mongo/player.repository'
import {PlayerConverter} from './player.converter'

@Injectable()
export class PlayerOutAdapter implements PlayerOutPort {
  constructor(
    private readonly playerRepository: PlayerRepository,
    private readonly playerConverter: PlayerConverter
  ) {}

  getOne(id: PlayerId): Promise<Player | null> {
    return this.playerRepository
      .getOne(id)
      .then((player) => this.playerConverter.toPlayer(player))
  }

  getBy(squadId: string): Promise<Player[]> {
    return this.playerRepository
      .getBySquadId(squadId)
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
}
