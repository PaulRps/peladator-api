import {Module} from '@nestjs/common'
import {PlayerRestService} from './application/services/player-rest.service'
import {SquadRestService} from './application/services/squad-rest.service'
import {CreatePlayer} from './application/use-cases/create-player.usecase'
import {CreateSquad} from './application/use-cases/create-squad.usecase'
import {DeletePlayer} from './application/use-cases/delete-player.usecase'
import {DeleteSquad} from './application/use-cases/delete-squad.usecase'
import {FilterPlayers} from './application/use-cases/filter-players.usecase'
import {GetOnePlayer} from './application/use-cases/get-one-player.usecase'
import {GetOneSquad} from './application/use-cases/get-one-squad.usecase'
import {UpdatePlayer} from './application/use-cases/update-player.usecase'
import {UpdateSquad} from './application/use-cases/update-squad.usecase'
import {PlayerRestInAdapter} from './infra/adapters/in/player-rest-in.adapter'
import {SquadRestInAdapter} from './infra/adapters/in/squad-rest-in.adapter'
import {PersistenceModule} from './infra/config/persistence/persistence.module'
import {PLAYER_OUT_PORT} from './application/ports/out/player-out.port'
import {SQUAD_OUT_PORT} from './application/ports/out/squad-out.port'
import {PlayerOutAdapter} from './infra/adapters/out/persistence/mongo/player-out.adapter'
import {PlayerConverter} from './infra/adapters/out/persistence/mongo/player.converter'
import {SquadOutAdapter} from './infra/adapters/out/persistence/mongo/squad-out.adapter'
import {SquadConverter} from './infra/adapters/out/persistence/mongo/squad.converter'
import {CoreModule} from '../core/core.module'

@Module({
  controllers: [SquadRestInAdapter, PlayerRestInAdapter],
  providers: [
    SquadRestService,
    PlayerRestService,
    CreateSquad,
    DeleteSquad,
    GetOneSquad,
    UpdateSquad,
    CreatePlayer,
    DeletePlayer,
    GetOnePlayer,
    UpdatePlayer,
    FilterPlayers,
    {
      provide: SQUAD_OUT_PORT,
      useClass: SquadOutAdapter
    },
    {
      provide: PLAYER_OUT_PORT,
      useClass: PlayerOutAdapter
    },
    PlayerConverter,
    SquadConverter
  ],
  imports: [CoreModule, PersistenceModule]
})
export class SquadModule {}
