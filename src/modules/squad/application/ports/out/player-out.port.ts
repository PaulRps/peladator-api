import {Player, PlayerId} from 'src/modules/squad/domain/player'

export const PLAYER_OUT_PORT = 'PLAYER_OUT_PORT'
export interface PlayerOutPort {
  getOne(id: PlayerId): Promise<Player | null>
  getBy(squadId: string): Promise<Player[]>
  create(player: Player): Promise<PlayerId>
  update(player: Player): Promise<void>
  delete(id: PlayerId): Promise<void>
}
