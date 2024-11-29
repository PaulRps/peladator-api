import {Module} from '@nestjs/common'
import {CoreModule} from '../core/core.module'
import {FixtureDatabaseOutPort} from './application/ports/out/fixture-database-out.port'
import {PlayerDatabaseOutPort} from './application/ports/out/player-database-out.port'
import {SquadDatabaseOutPort} from './application/ports/out/squad-database-out.port'
import {FixtureRestService} from './application/services/fixture-rest.service'
import {PlayerRestService} from './application/services/player-rest.service'
import {SquadRestService} from './application/services/squad-rest.service'
import {CreateFixture} from './application/use-cases/create-fixture.usecase'
import {CreatePlayer} from './application/use-cases/create-player.usecase'
import {CreateSquad} from './application/use-cases/create-squad.usecase'
import {DeletePlayer} from './application/use-cases/delete-player.usecase'
import {DeleteSquad} from './application/use-cases/delete-squad.usecase'
import {FilterPlayers} from './application/use-cases/filter-players.usecase'
import {GetLatestFixture} from './application/use-cases/get-latest-fixture.usecase'
import {GetOnePlayer} from './application/use-cases/get-one-player.usecase'
import {GetOneSquad} from './application/use-cases/get-one-squad.usecase'
import {GetPlayerPositions} from './application/use-cases/get-player-positions.usecase'
import {GetPlayersForFixture} from './application/use-cases/get-players-for-fixture.usecase'
import {SavePlayerForFixture} from './application/use-cases/save-player-for-fixture.usecase'
import {SquadExists} from './application/use-cases/squad-exists.usecase'
import {UpdateFixture} from './application/use-cases/update-fixture.usecase'
import {UpdatePlayer} from './application/use-cases/update-player.usecase'
import {UpdateSquad} from './application/use-cases/update-squad.usecase'
import {FixtureRestInAdapter} from './infra/adapters/in/fixture-rest-in.adapter'
import {PlayerRestInAdapter} from './infra/adapters/in/player-rest-in.adapter'
import {SquadRestInAdapter} from './infra/adapters/in/squad-rest-in.adapter'
import {FixtureMongoOutAdapter} from './infra/adapters/out/persistence/mongo/fixture-mongo-out.adapter'
import {PlayerMongoOutAdapter} from './infra/adapters/out/persistence/mongo/player-mongo-out.adapter'
import {SquadMongoOutAdapter} from './infra/adapters/out/persistence/mongo/squad-mongo-out.adapter'
import {PersistenceModule} from './infra/adapters/out/persistence/persistence.module'
import {DeleteFixture} from './application/use-cases/delete-fixture.usecase'
import {GetPlayersFixtureHistory} from './application/use-cases/get-players-fixture-history.usecase'

@Module({
  controllers: [SquadRestInAdapter, PlayerRestInAdapter, FixtureRestInAdapter],
  providers: [
    SquadRestService,
    PlayerRestService,
    FixtureRestService,
    CreateSquad,
    DeleteSquad,
    GetOneSquad,
    UpdateSquad,
    SquadExists,
    CreatePlayer,
    DeletePlayer,
    GetOnePlayer,
    UpdatePlayer,
    FilterPlayers,
    CreateFixture,
    GetLatestFixture,
    GetPlayerPositions,
    SavePlayerForFixture,
    GetPlayersForFixture,
    UpdateFixture,
    DeleteFixture,
    GetPlayersFixtureHistory,
    {
      provide: SquadDatabaseOutPort.name,
      useClass: SquadMongoOutAdapter
    },
    {
      provide: PlayerDatabaseOutPort.name,
      useClass: PlayerMongoOutAdapter
    },
    {
      provide: FixtureDatabaseOutPort.name,
      useClass: FixtureMongoOutAdapter
    }
  ],
  imports: [CoreModule, PersistenceModule]
})
export class SquadModule {}
