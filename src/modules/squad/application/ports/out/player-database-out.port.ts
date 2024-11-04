import {Player, PlayerId} from 'src/modules/squad/domain/player'

export const PLAYER_DATABASE_OUT_PORT = 'PLAYER_DATABASE_OUT_PORT'
export interface PlayerDatabaseOutPort {
  getOne(id: PlayerId): Promise<Player | null>
  getBy(squadId: string): Promise<Player[]>
  create(player: Player): Promise<PlayerId>
  update(player: Player): Promise<void>
  delete(id: PlayerId): Promise<void>
  savePlayerForFixture(player: Player): Promise<void>
  getPlayersForFixture(squadId: string): Promise<Player[]>
  existsPlayerForFixture(playerId: PlayerId, squadId: string): Promise<boolean>
}
