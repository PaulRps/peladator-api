import { Injectable } from "@nestjs/common";
import { Types } from "mongoose";
import { Player } from "src/modules/squad/domain/player";
import * as playerDocument from "src/modules/squad/infra/config/persistence/mongo/player.document";

@Injectable()
export class PlayerConverter {
  toPlayerDocument(player: Player): playerDocument.Player {
    return new playerDocument.Player({
      id: player?.id ? new Types.ObjectId(player?.id) : null,
      name: player?.name,
      position: player?.position,
      level: player?.level,
      squadId: new Types.ObjectId(player?.squadId)
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
}