import {Player, PlayerId} from 'src/modules/squad/domain/player'

export const PLAYER_IN_PORT = 'PLAYER_IN_PORT'
export interface PlayerRestInPort {
  create(player: Player): Promise<Player>
  getOne(id: PlayerId): Promise<Player | null>
  getBy(squadId: string): Promise<Player[]>
  update(player: Player): Promise<void>
  delete(id: PlayerId): Promise<void>
  getPlayerPositions(): Promise<string[]>
  savePlayerForFixture(player: Player): Promise<void>
  getPlayersForFixture(squadId: string): Promise<Player[]>
}
