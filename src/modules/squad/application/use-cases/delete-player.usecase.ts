import { Inject, Injectable } from "@nestjs/common";
import { PLAYER_OUT_PORT, PlayerOutPort } from "../ports/out/player-out.port";
import { PlayerId } from "../../domain/player";

@Injectable()
export class DeletePlayer {
  constructor(
    @Inject(PLAYER_OUT_PORT) private readonly playerOutPort: PlayerOutPort
  ) {}

  execute(id: PlayerId): Promise<void> {
   return this.playerOutPort.delete(id)
  }
}