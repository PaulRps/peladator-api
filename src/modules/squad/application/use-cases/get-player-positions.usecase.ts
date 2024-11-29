import {Injectable} from '@nestjs/common'
import {PlayerPosition} from '../../domain/models/player-position'

@Injectable()
export class GetPlayerPositions {
  execute(): Promise<string[]> {
    return Promise.resolve(Object.values(PlayerPosition))
  }
}
