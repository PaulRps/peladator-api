import {Inject, Injectable} from '@nestjs/common'
import {Player} from '../../domain/player'
import {PlayerDatabaseOutPort} from '../ports/out/player-database-out.port'
import {SquadExists} from './squad-exists'

@Injectable()
export class CreatePlayer {
  constructor(
    @Inject(PlayerDatabaseOutPort.name)
    private readonly playerOutPort: PlayerDatabaseOutPort,
    private readonly squadExists: SquadExists
  ) {}

  async execute(player: Player): Promise<Player> {
    const squadExists = await this.squadExists.execute(player.squadId)

    if (!squadExists) {
      throw new Error('Squad not found')
    }

    return this.playerOutPort.create(player).then((id) => new Player({id}))
  }
}
