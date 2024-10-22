import {Injectable} from '@nestjs/common'
import {SquadOutPort} from 'src/modules/squad/application/ports/out/squad-out.port'
import {Squad, SquadId} from 'src/modules/squad/domain/squad'
import {PlayerRepository} from 'src/modules/squad/infra/config/persistence/mongo/player.repository'
import {SquadRepository} from 'src/modules/squad/infra/config/persistence/mongo/squad.repository'
import {PlayerConverter} from './player.converter'
import {SquadConverter} from './squad.converter'

@Injectable()
export class SquadOutAdapter implements SquadOutPort {
  constructor(
    private readonly squadRepository: SquadRepository,
    private readonly playerRepository: PlayerRepository,
    private readonly playerConverter: PlayerConverter,
    private readonly squadConverter: SquadConverter
  ) {}
  getOne(id: SquadId): Promise<Squad | null> {
    return this.squadRepository.getById(id).then(async (squad) => {
      if (!squad) return null

      return this.squadConverter.toSquad(
        squad,
        (await this.playerRepository.getBySquadId(id))?.map((player) =>
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
}
