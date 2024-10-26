import {Inject, Injectable} from '@nestjs/common'
import {Player, PlayerId} from '../../domain/player'
import {
  PLAYER_DATABASE_OUT_PORT,
  PlayerDatabaseOutPort
} from '../ports/out/player-database-out.port'
import {SquadExists} from './squad-exists'

@Injectable()
export class CreatePlayer {
  constructor(
    @Inject(PLAYER_DATABASE_OUT_PORT)
    private readonly playerOutPort: PlayerDatabaseOutPort,
    private readonly squadExists: SquadExists
  ) {}

  async execute(player: Player): Promise<PlayerId> {
    const squadExists = await this.squadExists.execute(player.squadId)

    if (!squadExists) {
      throw new Error('Squad not found')
    }

    return this.playerOutPort.create(player)
  }
}
