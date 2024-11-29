import {Squad, SquadId} from 'src/modules/squad/domain/models/squad'

export interface SquadRestInPort {
  getOne(id: string): Promise<Squad | null>

  create(squad: Squad): Promise<SquadId>

  update(squad: Squad): Promise<void>

  delete(id: string): Promise<void>
}
