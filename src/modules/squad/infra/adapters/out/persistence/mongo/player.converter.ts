import {Injectable} from '@nestjs/common'
import {Types} from 'mongoose'
import {Player} from 'src/modules/squad/domain/models/player'
import * as playerDocument from 'src/modules/squad/infra/config/persistence/mongo/player.document'
import * as playerForFixtureDocument from 'src/modules/squad/infra/config/persistence/mongo/player-for-fixture.document'

@Injectable()
export class PlayerConverter {
  toPlayerDocument(from: Player): playerDocument.Player {
    return new playerDocument.Player({
      id: from?.id ? new Types.ObjectId(from?.id) : null,
      name: from?.name,
      position: from?.position,
      level: from?.level,
      squadId: new Types.ObjectId(from?.squadId)
    })
  }

  toPlayer(from: playerDocument.PlayerDocument): Player {
    return new Player({
      id: from?._id?.toString(),
      name: from?.name,
      position: from?.position,
      level: from?.level,
      squadId: from?.squadId?.toString()
    })
  }

  toPlayerForFixtureDocument(
    player: Player
  ): playerForFixtureDocument.PlayerForFixture {
    return new playerForFixtureDocument.PlayerForFixture({
      id: player?.id ? new Types.ObjectId(player?.id) : null,
      name: player?.name,
      position: player?.position,
      level: player?.level,
      squadId: new Types.ObjectId(player?.squadId)
    })
  }

  toPlayerForFixture(
    from: playerForFixtureDocument.PlayerForFixtureDocument
  ): Player {
    return new Player({
      id: from?._id?.toString(),
      name: from?.name,
      position: from?.position,
      level: from?.level,
      squadId: from?.squadId?.toString()
    })
  }
}
