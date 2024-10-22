import {Injectable} from '@nestjs/common'
import { Types } from 'mongoose'
import {Player} from 'src/modules/squad/domain/player'
import {Squad} from 'src/modules/squad/domain/squad'
import * as squadDocument from 'src/modules/squad/infra/config/persistence/mongo/squad.document'

@Injectable()
export class SquadConverter {
  toSquadDocument(squad: Squad): squadDocument.Squad {
    return new squadDocument.Squad({
      name: squad?.name,
      id: squad?.id ? new Types.ObjectId(squad.id) : null,
      players: squad?.players?.map((player) => new Types.ObjectId(player.id))
    })
  }

  toSquad(squad: squadDocument.SquadDocument, players: Player[]): Squad {
    return new Squad({
      id: squad?._id?.toString(),
      name: squad?.name,
      players
    })
  }
}
