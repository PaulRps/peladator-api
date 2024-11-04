import {Inject, Injectable} from '@nestjs/common'
import {Player} from '../../domain/player'
import {
  PLAYER_DATABASE_OUT_PORT,
  PlayerDatabaseOutPort
} from '../ports/out/player-database-out.port'

@Injectable()
export class SavePlayerForFixture {
  constructor(
    @Inject(PLAYER_DATABASE_OUT_PORT)
    private readonly playerOutPort: PlayerDatabaseOutPort
  ) {}

  async execute(player: Player): Promise<void> {
    const exists = await this.playerOutPort.existsPlayerForFixture(
      player.id,
      player.squadId
    )

    if (exists) {
      throw new Error(`Player ${player} already exists for fixture'`)
    }

    return this.playerOutPort.savePlayerForFixture(player)
  }
}
