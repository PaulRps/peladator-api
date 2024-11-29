import {Player, PlayerId} from 'src/modules/squad/domain/models/player'

export interface PlayerDatabaseOutPort {
  getOne(id: PlayerId): Promise<Player | null>
  getBy(squadId: string, ids?: PlayerId[]): Promise<Player[]>
  create(player: Player): Promise<PlayerId>
  update(player: Player): Promise<void>
  delete(id: PlayerId): Promise<void>
  savePlayerForFixture(player: Player): Promise<void>
  getPlayersForFixture(squadId: string): Promise<Player[]>
  existsPlayerForFixture(playerId: PlayerId, squadId: string): Promise<boolean>
}

export namespace PlayerDatabaseOutPort {
  export const name = 'PlayerDatabaseOutPort'
}
