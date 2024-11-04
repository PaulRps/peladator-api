import {Module} from '@nestjs/common'
import {MongooseModule} from '@nestjs/mongoose'
import {Fixture, FixtureSchema} from './fixture.document'
import {Player, PlayerSchema} from './player.document'
import {Squad, SquadSchema} from './squad.document'
import {
  PlayerForFixture,
  PlayerForFixtureSchema
} from './player-for-fixture.document'

const mongoSchemas = MongooseModule.forFeature([
  {name: Player.name, schema: PlayerSchema},
  {name: Squad.name, schema: SquadSchema},
  {name: Fixture.name, schema: FixtureSchema},
  {name: PlayerForFixture.name, schema: PlayerForFixtureSchema}
])

@Module({
  imports: [mongoSchemas],
  exports: [mongoSchemas]
})
export class MongoConfigModule {}
