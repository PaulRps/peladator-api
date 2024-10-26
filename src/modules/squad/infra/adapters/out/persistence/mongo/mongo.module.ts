import {Module} from '@nestjs/common'
import {PlayerRepository} from './player.repository'
import {SquadRepository} from './squad.repository'
import {FixtureRepository} from './fixture.repository'
import {MongoConfigModule} from 'src/modules/squad/infra/config/persistence/mongo/mongo-config.module'
import {FixtureConverter} from './fixture.converter'
import {PlayerConverter} from './player.converter'
import {SquadConverter} from './squad.converter'

@Module({
  imports: [MongoConfigModule],
  providers: [
    PlayerRepository,
    SquadRepository,
    FixtureRepository,
    PlayerConverter,
    SquadConverter,
    FixtureConverter
  ],
  exports: [
    PlayerRepository,
    SquadRepository,
    FixtureRepository,
    PlayerConverter,
    SquadConverter,
    FixtureConverter
  ]
})
export class MongoModule {}
