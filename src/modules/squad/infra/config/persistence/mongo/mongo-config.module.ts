import {Module} from '@nestjs/common'
import {MongooseModule} from '@nestjs/mongoose'
import {Fixture, FixtureSchema} from './fixture.document'
import {Player, PlayerSchema} from './player.document'
import {Squad, SquadSchema} from './squad.document'

const mongoSchemas = MongooseModule.forFeature([
  {name: Player.name, schema: PlayerSchema},
  {name: Squad.name, schema: SquadSchema},
  {name: Fixture.name, schema: FixtureSchema}
])

@Module({
  imports: [mongoSchemas],
  exports: [mongoSchemas]
})
export class MongoConfigModule {}
