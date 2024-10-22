import {Squad, SquadId} from 'src/modules/squad/domain/squad'

export interface SquadInPort {
  getOne(id: string):  Promise<Squad | null>

  create(squad: Squad): Promise<SquadId>

  update(squad: Squad): Promise<void>

  delete(id: string): Promise<void>
}
