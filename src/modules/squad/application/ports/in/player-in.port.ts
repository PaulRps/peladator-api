import {Player, PlayerId} from 'src/modules/squad/domain/player'

export const PLAYER_IN_PORT = 'PLAYER_IN_PORT'
export interface PlayerInPort {
  create(player: Player): Promise<PlayerId>
  getOne(id: PlayerId): Promise<Player | null>
  getBy(squadId: string): Promise<Player[]>
  update(player: Player): Promise<void>
  delete(id: PlayerId): Promise<void>
}
