import {Injectable} from '@nestjs/common'
import {SquadDatabaseOutPort} from 'src/modules/squad/application/ports/out/squad-database-out.port'
import {Fixture} from 'src/modules/squad/domain/fixture'
import {Squad, SquadId} from 'src/modules/squad/domain/squad'
import {PlayerRepository} from 'src/modules/squad/infra/adapters/out/persistence/mongo/player.repository'
import {SquadRepository} from 'src/modules/squad/infra/adapters/out/persistence/mongo/squad.repository'
import {PlayerConverter} from './player.converter'
import {SquadConverter} from './squad.converter'

@Injectable()
export class SquadMongoOutAdapter implements SquadDatabaseOutPort {
  constructor(
    private readonly squadRepository: SquadRepository,
    private readonly playerRepository: PlayerRepository,
    private readonly playerConverter: PlayerConverter,
    private readonly squadConverter: SquadConverter
  ) {}
  createFixture(fixture: Fixture): Promise<Fixture> {
    throw new Error('Method not implemented.')
  }

  getOne(id: SquadId): Promise<Squad | null> {
    return this.squadRepository.getById(id).then(async (squad) => {
      if (!squad) return null

      return this.squadConverter.toSquad(
        squad,
        (await this.playerRepository.getBy(id))?.map((player) =>
          this.playerConverter.toPlayer(player)
        )
      )
    })
  }

  create(squad: Squad): Promise<SquadId> {
    return this.squadRepository.save(this.squadConverter.toSquadDocument(squad))
  }

  update(squad: Squad): Promise<void> {
    return this.squadRepository.update(
      this.squadConverter.toSquadDocument(squad)
    )
  }

  delete(id: SquadId): Promise<void> {
    return this.squadRepository.delete(id).then(() => {
      this.playerRepository.deleteBySquadId(id)
    })
  }

  exists(squadId: SquadId): Promise<boolean> {
    return this.squadRepository.exists(squadId)
  }
}
