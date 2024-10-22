import {Injectable} from '@nestjs/common'
import {Squad, SquadId} from '../../domain/squad'
import {CreateSquad} from '../use-cases/create-squad.usecase'
import {DeleteSquad} from '../use-cases/delete-squad.usecase'
import {GetOneSquad} from '../use-cases/get-one-squad.usecase'
import {UpdateSquad} from '../use-cases/update-squad.usecase'

@Injectable()
export class SquadRestService {
  constructor(
    private readonly createSquad: CreateSquad,
    private readonly updateSquad: UpdateSquad,
    private readonly deleteSquad: DeleteSquad,
    private readonly getOneSquad: GetOneSquad
  ) {}

  create(squad: Squad): Promise<SquadId> {
    return this.createSquad.execute(squad)
  }

  getOne(id: SquadId):  Promise<Squad> | null {
    return this.getOneSquad.execute(id)
  }

  update(squad: Squad): Promise<void> {
    return this.updateSquad.execute(squad)
  }

  delete(id: SquadId): Promise<void> {
    return this.deleteSquad.execute(id)
  }
}
