import { Module } from "@nestjs/common";
import { MongooseModule } from "@nestjs/mongoose";
import { Player, PlayerSchema } from "./player.document";
import { PlayerRepository } from "./player.repository";
import { Squad, SquadSchema } from "./squad.document";
import { SquadRepository } from "./squad.repository";

@Module({
  imports: [MongooseModule.forFeature([
    { name: Player.name, schema: PlayerSchema },
    { name: Squad.name, schema: SquadSchema }
  ])],
  providers: [PlayerRepository, SquadRepository],
  exports: [PlayerRepository, SquadRepository]
})
export class MongoModule {}