import { Inject, Injectable } from "@nestjs/common";
import { PLAYER_OUT_PORT, PlayerOutPort } from "../ports/out/player-out.port";
import { Player, PlayerId } from "../../domain/player";

@Injectable()
export class GetOnePlayer {
  constructor(
    @Inject(PLAYER_OUT_PORT) private readonly playerOutPort: PlayerOutPort
  ) {}

  execute(id: PlayerId): Promise<Player | null> {
    return this.playerOutPort.getOne(id)
  }
}