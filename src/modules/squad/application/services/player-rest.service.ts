import {Injectable} from '@nestjs/common'
import {Player, PlayerId} from '../../domain/player'
import {CreatePlayer} from '../use-cases/create-player.usecase'
import {DeletePlayer} from '../use-cases/delete-player.usecase'
import {FilterPlayers} from '../use-cases/filter-players.usecase'
import {GetOnePlayer} from '../use-cases/get-one-player.usecase'
import {UpdatePlayer} from '../use-cases/update-player.usecase'

@Injectable()
export class PlayerRestService {
  constructor(
    private readonly createPlayer: CreatePlayer,
    private readonly updatePlayer: UpdatePlayer,
    private readonly getOnePlayer: GetOnePlayer,
    private readonly deletePlayer: DeletePlayer,
    private readonly filterPlayers: FilterPlayers
  ) {}

  create(player: Player): Promise<PlayerId> {
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

  getBy(squadId: string): Promise<Player[]> {
    return this.filterPlayers.execute(squadId)
  }
}
